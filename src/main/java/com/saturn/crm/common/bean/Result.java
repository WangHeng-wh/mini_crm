package com.saturn.crm.common.bean;

import com.saturn.crm.common.enums.StatusCode;

/**
 * Desc:
 * Author: Saturn_Wh
 * Date: 2025/6/27 16:25
 */
public class Result<T> {
    private String code;
    private String message;
    private T data;

    private Result() {

    }

    private static <T> Result<T> create(StatusCode statusCode, Object data, String message) {
        Result<T> result = new Result<>();
        result.code = statusCode.getCode();
        result.message = (message == null ? statusCode.getExplain() : message);
        result.data = ((T) data);
        return result;
    }

    public static <T> Result<T> error(StatusCode statusCode) {
        return create(statusCode, null, null);
    }

    public static <T> Result<T> error(StatusCode statusCode, String message) {
        return create(statusCode, null, message);
    }

    public static <T> Result<T> success() {
        return create(StatusCode.SUCCESS_1000, null, null);
    }

    public static <T> Result<T> success(Object data) {
        return create(StatusCode.SUCCESS_1000, data, null);
    }

    public static <T> Result<T> success(String message, Object data) {
        return create(StatusCode.SUCCESS_1000, data, message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
