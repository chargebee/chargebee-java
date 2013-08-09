package com.chargebee.internal;

import com.chargebee.*;
import org.apache.commons.codec.binary.Base64;
import org.json.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.zip.GZIPInputStream;

public class HttpUtil {

    public enum Method {
        GET, POST, PUT, DELETE;
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

    public static Result get(String url, Params params, Environment env) throws IOException {
        if(params != null && !params.isEmpty()) {
            url = url + '?' + toQueryStr(params); // fixme: what about url size restrictions ??
        }
        HttpURLConnection conn = createConnection(url, Method.GET, env);
        Resp resp = sendRequest(conn);
        return resp.toResult();
    }

    public static ListResult getList(String url, Params params, Environment env) throws IOException {
        if(params != null && !params.isEmpty()) {
            url = url + '?' + toQueryStr(params); // fixme: what about url size restrictions ??
        }
        HttpURLConnection conn = createConnection(url, Method.GET, env);
        Resp resp = sendRequest(conn);
        return resp.toListResult();
    }

    public static Result post(String url, Params params, Environment env) throws IOException {
        return doFormSubmit(url,Method.POST, toQueryStr(params), env);
    }

    public static Result put(String url, Params params, Environment env) throws IOException {
        return doFormSubmit(url,Method.PUT, toQueryStr(params), env);
    }

    public static String toQueryStr(Params map) {
        StringJoiner buf = new StringJoiner("&");
        for (Map.Entry<String, String> entry : map.entries()) {
            String keyValPair = enc(entry.getKey()) + "=" + enc(entry.getValue());
            buf.add(keyValPair);
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

    private static Result doFormSubmit(String url,Method m, String queryStr, Environment env) throws IOException {
        HttpURLConnection conn = createConnection(url, m, env);
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

    private static HttpURLConnection createConnection(String url, Method m, Environment config)
            throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod(m.name());
        setTimeouts(conn, config);
        addHeaders(conn, config);
        setContentType(conn, m);
        if ((m == Method.POST) || (m == Method.PUT)) {
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
        JSONObject jsonResp = getContentAsJSON(conn, error);
        if(error) {
            throw new APIException(jsonResp);
        }
        return new Resp(httpRespCode, jsonResp);
    }

    private static void setTimeouts(URLConnection conn, Environment config) {
        conn.setConnectTimeout(config.connectTimeout);
        conn.setReadTimeout(config.readTimeout);
    }

    private static void setContentType(HttpURLConnection conn, Method m) {
        if ((m == Method.POST) || (m == Method.PUT)) {
            addHeader(conn, "Content-Type", "application/x-www-form-urlencoded;charset=" + Environment.CHARSET);
        }
    }

    private static void addHeaders(HttpURLConnection conn, Environment config) {
        addHeader(conn, "Accept-Charset", Environment.CHARSET);
        addHeader(conn, "User-Agent", String.format("Chargebee-Java-Client v%s", Environment.LIBRARY_VERSION));
        addHeader(conn, "Authorization", getAuthValue(config));
        addHeader(conn, "Accept", "application/json");
    }

    private static void addHeader(HttpURLConnection conn, String headerName, String value) {
        conn.setRequestProperty(headerName, value);
    }

    private static String getAuthValue(Environment config) {
        return "Basic " + Base64.encodeBase64String((config.apiKey + ":").getBytes())
                .replaceAll("\r?", "").replaceAll("\n?", "");
    }

    private static JSONObject getContentAsJSON(HttpURLConnection conn, boolean error) throws IOException {
        String content = getContentAsString(conn, error);
        JSONObject obj;
        try {
            obj = new JSONObject(content);
        } catch (JSONException exp) {
            throw new RuntimeException(exp.getMessage());
        }
        checkRequiredJSONResp(obj);
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

    private static void checkRequiredJSONResp(JSONObject respObj) {
        if (respObj == null) {
            throw new RuntimeException("Expected json formatted content in response");
        }
    }
}
