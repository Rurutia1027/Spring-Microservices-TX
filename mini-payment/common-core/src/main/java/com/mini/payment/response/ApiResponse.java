package com.mini.payment.response;

import org.apache.hc.core5.http.HttpStatus;

public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private int status;

    public ApiResponse(boolean success, String message, T data, int status) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, null, data, HttpStatus.SC_OK);
    }

    public static <T> ApiResponse<T> success(T data, int statusCode) {
        return new ApiResponse<>(true, null, data, statusCode);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<>(false, message, null, HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    public static <T> ApiResponse<T> fail(String message, int statusCode) {
        return new ApiResponse<>(false, message, null, statusCode);
    }

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
