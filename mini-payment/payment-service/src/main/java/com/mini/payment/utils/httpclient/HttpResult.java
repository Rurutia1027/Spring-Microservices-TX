package com.mini.payment.utils.httpclient;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpResult {

    private int statusCode;
    private String content;
    private String exceptionMsg;
    private Exception exception;
    private Map<String, List<String>> headers;
    private String contentType;


    public HttpResult(int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpResult(int statusCode, String content) {
        this.statusCode = statusCode;
        this.content = content;
    }

    public HttpResult(Exception e) {
        if (Objects.isNull(e)) {
            throw new IllegalArgumentException("Exception must be specified");
        }
        this.statusCode = -1;
        this.exception = e;
        this.exceptionMsg = e.getMessage();
    }

    public boolean isSuccess() {
        return statusCode == 200 || statusCode == 201;
    }

    public boolean isError() {
        return Objects.nonNull(exception);
    }

    // -- getter && setter --
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
