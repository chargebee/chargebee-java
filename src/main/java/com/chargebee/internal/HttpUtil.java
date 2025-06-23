package com.chargebee.internal;

import com.chargebee.*;
import com.chargebee.exceptions.*;
import java.io.*;
import java.net.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.GZIPInputStream;

import org.apache.commons.codec.binary.Base64;
import org.json.*;

public class HttpUtil {

    public enum Method {
        GET, POST;
    }

    public static final String CONTENT_TYPE_HEADER_NAME = "Content-Type";

    private static class Resp {
        int httpCode;
        JSONObject jsonContent;
        public Map<String, List<String>> responseHeaders;

        private Resp(int httpCode, JSONObject jsonContent, Map<String, List<String>> responseHeaders) {
            this.httpCode = httpCode;
            this.jsonContent = jsonContent;
            this.responseHeaders = responseHeaders;
        }

        private Result toResult() {
            return new Result(httpCode, jsonContent, responseHeaders);
        }

        private ListResult toListResult() {
            return new ListResult(httpCode, jsonContent, responseHeaders);
        }
    }

    public static Result get(String url, Params params, Map<String,String> headers, Environment env) throws IOException {
        if(params != null && !params.isEmpty()) {
            url = url + '?' + toQueryStr(params); // fixme: what about url size restrictions ??
        }
        HttpURLConnection conn = createConnection(url, Method.GET, headers, env);
        Resp resp = sendRequestWithRetry(conn, env);
        return resp.toResult();
    }

    public static ListResult getList(String url, Params params, Map<String,String> headers, Environment env) throws IOException {
        if(params != null && !params.isEmpty()) {
            url = url + '?' + toQueryStr(params, true); // fixme: what about url size restrictions ??
        }
        HttpURLConnection conn = createConnection(url, Method.GET, headers, env);
        Resp resp = sendRequestWithRetry(conn, env);
        return resp.toListResult();
    }

    public static Result post(String url, Params params, Map<String,String> headers, Environment env) throws IOException {
        return doFormSubmit(url, Method.POST, toQueryStr(params), headers, env);
    }

    public static Result post(String url, String content, Map<String,String> headers, Environment env) throws IOException {
        return doFormSubmit(url, Method.POST, content, headers, env);
    }


    public static String toQueryStr(Params map) {
        return toQueryStr(map, false);
    }

    public static String toQueryStr(Params map, boolean isListReq) {
        StringJoiner buf = new StringJoiner("&");
        for (Map.Entry<String, Object> entry : map.entries()) {
            Object value = entry.getValue();
            if (value instanceof List) {
                List<String> l = (List<String>) value;
                if (isListReq) {
                    buf.add(enc(entry.getKey()) + "=" + enc(l.isEmpty() ? "" : l.toString()));
                    continue;
                }
                for (int i = 0; i < l.size(); i++) {
                    String val = l.get(i);
                    buf.add(enc(entry.getKey() + "[" + i + "]") + "=" + enc(val != null ? val : ""));
                }
            } else {
                buf.add(enc(entry.getKey()) + "=" + enc((String)value));
            }
        }
        return buf.toString();
    }

    private static String enc(String val) {
        try {
            return URLEncoder.encode(val, Environment.CHARSET);
        } catch (Exception exp) {
            throw new RuntimeException(exp);
        }
    }

    private static Result doFormSubmit(String url, Method m, String queryStr, Map<String,String> headers, Environment env) throws IOException {
        HttpURLConnection conn = createConnection(url, m, headers, env);
        writeContent(conn, queryStr);
        Resp resp = sendRequestWithRetry(conn, env);
        return resp.toResult();
    }

    private static void writeContent(HttpURLConnection conn, String queryStr) throws IOException {
        if (queryStr == null) return;
        OutputStream os = conn.getOutputStream();
        try {
            os.write(queryStr.getBytes(Environment.CHARSET));
        } finally {
            os.close();
        }
    }

    static HttpURLConnection createConnection(String url, Method m, Map<String, String> headers, Environment config) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod(m.name());
        setTimeouts(conn, config);
        addHeaders(conn, config);
        addCustomHeaders(conn, headers);
        setContentType(conn, m, headers);
        if (m == Method.POST) conn.setDoOutput(true);
        conn.setUseCaches(false);
        return conn;
    }

    private static Resp sendRequestWithRetry(HttpURLConnection conn, Environment env) throws IOException {
        int attempt = 0;
        int lastRetryAfterDelay = 0;
        while (true) {
            try {
                return sendRequest(conn);
            } catch (Exception e) {
                int statusCode = extractStatusCode(e);
                // Retry if enabled and status code is retryable
                if (!env.retryConfig.isEnabled() || !env.retryConfig.shouldRetry(statusCode, attempt)) throw e;

                int retryAfterDelay = parseRetryAfterHeader(conn);
                int delay = getRetryDelay(conn, env.retryConfig, attempt, retryAfterDelay > 0 ? retryAfterDelay : lastRetryAfterDelay);
                logRetry(attempt, statusCode, delay, env.enableDebugLogging);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ignored) {}
                attempt++;
                lastRetryAfterDelay = retryAfterDelay > 0 ? retryAfterDelay : lastRetryAfterDelay;
                conn = recreateConnection(conn, env);
            }
        }
    }

    private static int getRetryDelay(HttpURLConnection conn, RetryConfig config, int attempt, int retryAfterDelay) {
        if (retryAfterDelay > 0) return retryAfterDelay;
        int jitter = new Random().nextInt(100);
        return config.getBaseDelayMs() * (int) Math.pow(2, attempt) + jitter;
    }

    private static int parseRetryAfterHeader(HttpURLConnection conn) {
        String retryAfter = conn.getHeaderField("Retry-After");
        if (retryAfter == null) return 0;
        try {
            return Integer.parseInt(retryAfter) * 1000;
        } catch (NumberFormatException e) {
            try {
                ZonedDateTime retryTime = ZonedDateTime.parse(retryAfter, DateTimeFormatter.RFC_1123_DATE_TIME);
                long delay = retryTime.toInstant().toEpochMilli() - System.currentTimeMillis();
                return (int)Math.max(delay, 0);
            } catch (Exception ignored) {}
        }
        return 0;
    }

    private static int extractStatusCode(Exception e) {
        if (e instanceof APIException) {
            return ((APIException) e).httpStatusCode;
        }
        String msg = e.getMessage();
        if (msg == null) return 0;
        if (msg.contains("503")) return 503;
        if (msg.contains("504")) return 504;
        if (msg.contains("429")) return 429;
        return 0;
    }

    private static HttpURLConnection recreateConnection(HttpURLConnection oldConn, Environment env) throws IOException {
        URL url = oldConn.getURL();
        String method = oldConn.getRequestMethod();
        Map<String, String> headers = new HashMap<>();
        return createConnection(url.toString(), Method.valueOf(method), headers, env);
    }

    private static void logRetry(int attempt, int statusCode, int delayMs, boolean enableDebugLogging) {
        if(enableDebugLogging) {
            System.out.println(String.format("[Retry] Attempt %d due to HTTP %d. Retrying in %d ms", attempt + 1, statusCode, delayMs));
        }
    }

    private static Resp sendRequest(HttpURLConnection conn) throws IOException {
        int httpRespCode = conn.getResponseCode();
        Map<String, List<String>> responseHeaders = conn.getHeaderFields();
        if (httpRespCode == HttpURLConnection.HTTP_NO_CONTENT) {
            throw new RuntimeException("Got http_no_content response");
        }
        boolean error = httpRespCode < 200 || httpRespCode > 299;
        String content = getContentAsString(conn, error);
        JSONObject jsonResp = getContentAsJSON(content);
        if (error) {
            try {
                jsonResp.getString("api_error_code");
                String type = jsonResp.optString("type");
                String exceptionMessage = jsonResp.getString("message");
                if (isBatchApi(conn)) {
                    throw new BatchAPIException(httpRespCode, exceptionMessage, jsonResp, responseHeaders);
                } else if ("payment".equals(type)) {
                    throw new PaymentException(httpRespCode, exceptionMessage, jsonResp, responseHeaders);
                } else if ("operation_failed".equals(type)) {
                    throw new OperationFailedException(httpRespCode, exceptionMessage, jsonResp, responseHeaders);
                } else if ("invalid_request".equals(type)) {
                    throw new InvalidRequestException(httpRespCode, exceptionMessage, jsonResp, responseHeaders);
                } else {
                    throw new APIException(httpRespCode, exceptionMessage, jsonResp, responseHeaders);
                }
            } catch (APIException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new RuntimeException("Error when parsing the error response. Probably not ChargeBee's error response. Content: \n " + content, ex);
            }
        }
        return new Resp(httpRespCode, jsonResp, responseHeaders);
    }


    private static void setTimeouts(URLConnection conn, Environment config) {
        conn.setConnectTimeout(config.connectTimeout);
        conn.setReadTimeout(config.readTimeout);
    }

    private static boolean isBatchApi(HttpURLConnection connection) {
        return connection.getURL().toString().contains("/batch/");
    }

    private static void setContentType(HttpURLConnection conn, Method m, Map<String,String> headers) {
        if (!headers.containsKey(CONTENT_TYPE_HEADER_NAME)) {
            if (m == Method.POST) {
                addHeader(conn, "Content-Type", "application/x-www-form-urlencoded;charset=" + Environment.CHARSET);
            }
        }
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
        try {
            return new JSONObject(content);
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
    }


    private static String getContentAsString(HttpURLConnection conn, boolean error) throws IOException {
        InputStream resp = (error) ? conn.getErrorStream() : conn.getInputStream();
        if (resp == null) throw new RuntimeException("Got Empty Response ");
        try {
            if ("gzip".equalsIgnoreCase(conn.getContentEncoding())) {
                resp = new GZIPInputStream(resp);
            }
            InputStreamReader inp = new InputStreamReader(resp, Environment.CHARSET);
            StringBuilder buf = new StringBuilder();
            char[] buffer = new char[1024];
            int bytesRead;
            while ((bytesRead = inp.read(buffer)) >= 0) {
                buf.append(buffer, 0, bytesRead);
            }
            return buf.toString();
        } finally {
            resp.close();
        }
    }
    
    

}