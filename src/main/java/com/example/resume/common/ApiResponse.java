package com.example.resume.common;

/**
 * 统一API返回结构 / Unified API response wrapper
 */
public class ApiResponse<T> {

    /** 是否成功 / Whether request is successful */
    private boolean success;

    /** 业务码 / Business code */
    private String code;

    /** 提示信息 / Human-readable message */
    private String message;

    /** 返回数据 / Payload data */
    private T data;

    public ApiResponse() {}

    public ApiResponse(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, "OK", "ok", data);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>(true, "CREATED", "created", data);
    }

    public static <T> ApiResponse<T> fail(String code, String message) {
        return new ApiResponse<>(false, code, message, null);
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}
