package com.example.resume.common;

/**
 * 业务异常（可带HTTP状态码）/ Business exception with HTTP status
 */
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;
    private final int httpStatus;

    public BusinessException(ErrorCode errorCode, int httpStatus, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public ErrorCode getErrorCode() { return errorCode; }
    public int getHttpStatus() { return httpStatus; }
}
