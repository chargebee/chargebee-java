package com.chargebee.internal;

import com.chargebee.*;
import com.chargebee.exceptions.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.zip.GZIPInputStream;
import org.apache.commons.codec.binary.Base64;
import org.json.*;

public class HttpUtil {

    public enum Method {
        GET, POST;
    }

    /**
     * To temporarily capture the http response
     */
    private static class Resp {
        int httpCode;
        JSONObject jsonContent;

        private Resp(int httpCode, JSONObject jsonContent) {
            this.httpCode = httpCode;
            this.jsonContent = jsonContent;
        }

        private Result toResult() {
            return new Result(httpCode, jsonContent);
        }

        private ListResult toListResult() {
            return new ListResult(httpCode, jsonContent);
        }
    }

    public static Result get(String url, Params params, Map<String,String> headers,Environment env) throws IOException {
        if(params != null && !params.isEmpty()) {
            url = url + '?' + toQueryStr(params); // fixme: what about url size restrictions ??
        }
        HttpURLConnection conn = createConnection(url, Method.GET, headers,env);
        Resp resp = sendRequest(conn);
        return resp.toResult();
    }

    public static ListResult getList(String url, Params params, Map<String,String> headers,Environment env) throws IOException {
        if(params != null && !params.isEmpty()) {
            url = url + '?' + toQueryStr(params, true); // fixme: what about url size restrictions ??
        }
        HttpURLConnection conn = createConnection(url, Method.GET, headers,env);
        Resp resp = sendRequest(conn);
        return resp.toListResult();
    }

    public static Result post(String url, Params params, Map<String,String> headers, Environment env) throws IOException {
        return doFormSubmit(url,Method.POST, toQueryStr(params), headers,env);
    }


    public static String toQueryStr(Params map) {
        return toQueryStr(map, false);
    }
    
    public static String toQueryStr(Params map, boolean isListReq) {
        StringJoiner buf = new StringJoiner("&");
        for (Map.Entry<String, Object> entry : map.entries()) {
            Object value = entry.getValue();            
            if(value instanceof List){
               List<String> l = (List<String>)value;
               if(isListReq){
                   String keyValPair = enc(entry.getKey()) + "=" + enc(l.isEmpty()?"": l.toString());
                   buf.add(keyValPair);
                   continue;
               }
                for (int i = 0; i < l.size(); i++) {
                    String val = l.get(i);
                    String keyValPair = enc(entry.getKey() + "[" + i + "]") + "=" + enc(val != null?val:"");
                    buf.add(keyValPair);
                }
            }else{
               String keyValPair = enc(entry.getKey()) + "=" + enc((String)value);                
               buf.add(keyValPair);
            }
        }
        return buf.toString();
    }

    private static String enc(String val) {
        try {
            return URLEncoder.encode(val, Environment.CHARSET);
        } catch(Exception exp) {
            throw new RuntimeException(exp);
        }
    }

    private static Result doFormSubmit(String url,Method m, String queryStr, Map<String,String> headers,
            Environment env) throws IOException {
        HttpURLConnection conn = createConnection(url, m, headers,env);
        writeContent(conn, queryStr);
        Resp resp = sendRequest(conn);
        return resp.toResult();
    }

    private static void writeContent(HttpURLConnection conn, String queryStr) throws IOException {
        if (queryStr == null) {
            return;
        }
        OutputStream os = conn.getOutputStream();
        try {
            os.write(queryStr.getBytes(Environment.CHARSET));
        } finally {
            os.close();
        }
    }

    private static HttpURLConnection createConnection(String url, Method m, 
            Map<String,String> headers,
            Environment config)
            throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod(m.name());
        setTimeouts(conn, config);
        addHeaders(conn, config);
        addCustomHeaders(conn,headers);
        setContentType(conn, m);
        if (m == Method.POST) {
            conn.setDoOutput(true);
        }
        conn.setUseCaches(false);
        return conn;
    }

    private static Resp sendRequest(HttpURLConnection conn) throws IOException {
        int httpRespCode = conn.getResponseCode();
        if (httpRespCode == HttpURLConnection.HTTP_NO_CONTENT) {
            throw new RuntimeException("Got http_no_content response");
        }
        boolean error = httpRespCode < 200 || httpRespCode > 299;
        String content = getContentAsString(conn, error);
        JSONObject jsonResp = getContentAsJSON(content);
        if(error) {
            try {
                jsonResp.getString("api_error_code");
                String type = jsonResp.optString("type");
                String excpetionMessage = jsonResp.getString("message");
                if ("payment".equals(type)) {
                    throw new PaymentException(httpRespCode, excpetionMessage, jsonResp);
                } else if ("operation_failed".equals(type)) {
                    throw new OperationFailedException(httpRespCode, excpetionMessage, jsonResp);
                } else if ("invalid_request".equals(type)) {
                    throw new InvalidRequestException(httpRespCode, excpetionMessage, jsonResp);
                } else{
                    throw new APIException(httpRespCode, excpetionMessage, jsonResp);
                }
            }catch(APIException ex){
                throw ex;            
            } catch (Exception ex) {
                throw new RuntimeException("Error when parsing the error response. Probably not ChargeBee' error response. The content is \n " + content, ex);
            }
        }
        return new Resp(httpRespCode, jsonResp);
    }

    private static void setTimeouts(URLConnection conn, Environment config) {
        conn.setConnectTimeout(config.connectTimeout);
        conn.setReadTimeout(config.readTimeout);
    }

    private static void setContentType(HttpURLConnection conn, Method m) {
        if (m == Method.POST) {
            addHeader(conn, "Content-Type", "application/x-www-form-urlencoded;charset=" + Environment.CHARSET);
        }
    }

    private static void addHeaders(HttpURLConnection conn, Environment config) {
        addHeader(conn, "Accept-Charset", Environment.CHARSET);
        addHeader(conn, "User-Agent", String.format("Chargebee-Java-Client v%s", Environment.LIBRARY_VERSION));
        addHeader(conn, "Authorization", getAuthValue(config));
        addHeader(conn, "Accept", "application/json");
        addHeader(conn, "OS-Version",String.format("%s  %s  %s",System.getProperty("os.name"),System.getProperty("os.arch"),System.getProperty("os.version")));
        addHeader(conn,"Lang-Version",System.getProperty("java.version"));
    }

    private static void addCustomHeaders(HttpURLConnection conn, Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            addHeader(conn, entry.getKey(), entry.getValue());
        }
    }

    private static void addHeader(HttpURLConnection conn, String headerName, String value) {
        conn.setRequestProperty(headerName, value);
    }

    private static String getAuthValue(Environment config) {
        return "Basic " + Base64.encodeBase64String((config.apiKey + ":").getBytes())
                .replaceAll("\r?", "").replaceAll("\n?", "");
    }

    private static JSONObject getContentAsJSON(String content) throws IOException {
        JSONObject obj;
        try {
            obj = new JSONObject(content);
        } catch (JSONException exp) {
            if (content.contains("503")){
                 throw new RuntimeException("Sorry, the server is currently unable to handle the request due to a temporary overload or scheduled maintenance. Please retry after sometime. \n type: internal_temporary_error, \n http_status_code: 503, \n error_code: internal_temporary_error,\n content: " + content,exp);
            }
            else if (content.contains("504")){
                 throw new RuntimeException("The server did not receive a timely response from an upstream server, request aborted. If this problem persists, contact us at support@chargebee.com. \n type: gateway_timeout, \n http_status_code: 504, \n error_code: gateway_timeout,\n content: " + content,exp);
            }
            else{
                 throw new RuntimeException("Sorry, something went wrong when trying to process the request. If this problem persists, contact us at support@chargebee.com. \n type: internal_error, \n http_status_code: 500, \n error_code: internal_error,\n " + content,exp);
            }
        }
        return obj;
    }

    private static String getContentAsString(HttpURLConnection conn, boolean error) throws IOException {

        InputStream resp = (error) ? conn.getErrorStream() : conn.getInputStream();
        if (resp == null) {
            throw new RuntimeException("Got Empty Response ");
        }
        try {
            if ("gzip".equalsIgnoreCase(conn.getContentEncoding())) {
                resp = new GZIPInputStream(resp);
            }
            InputStreamReader inp = new InputStreamReader(resp, Environment.CHARSET);
            StringBuilder buf = new StringBuilder();
            char[] buffer = new char[1024];//Should use content length.
            int bytesRead;
            while ((bytesRead = inp.read(buffer, 0, buffer.length)) >= 0) {
                buf.append(buffer, 0, bytesRead);
            }
            String content = buf.toString();
            return content;
        } finally {
            resp.close();
        }
    }
    
    

}
