package com.github.infrastructure.base.resp;

/**
 * @author EalenXie Created on 2020/2/28 15:07.
 * 为interfaces(用户界面层) 提供组件配置
 */
public class RespBody<T> {
    /**
     * 自定义业务码
     */
    private String code;
    /**
     * 自定义业务提示说明
     */
    private String message;
    /**
     * 自定义返回 数据结果集
     */
    private T body;

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

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public RespBody() {

    }

    public RespBody(String code) {
        this.code = code;
    }

    public RespBody(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public RespBody(String code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public static RespBody<Void> ok() {
        return ok(null);
    }

    public static <T> RespBody<T> ok(T body) {
        return build(RespCode.OK, body);
    }

    public static RespBody<ErrorBody> error(String code, String message, ErrorBody errorBody) {
        return build(code, message, errorBody);
    }

    public static RespBody<ErrorBody> error(ResultCode resultCode, ErrorBody errorBody) {
        return build(resultCode.getCode(), resultCode.getMessage(), errorBody);
    }

    public static RespBody<ErrorBody> error(ResultCode resultCode, String message, ErrorBody errorBody) {
        return build(resultCode.getCode(), message, errorBody);
    }

    public static <T> RespBody<T> fail(String message) {
        return fail(RespCode.FAIL, message, null);
    }

    public static <T> RespBody<T> fail(ResultCode resultCode) {
        return fail(resultCode, resultCode.getMessage(), null);
    }

    public static <T> RespBody<T> fail(ResultCode resultCode, String message, T data) {
        return build(resultCode.getCode(), message, data);
    }


    public static <T> RespBody<T> build(ResultCode resultCode, T body) {
        return build(resultCode.getCode(), resultCode.getMessage(), body);
    }

    /**
     * 以上所有构建均调用此底层方法
     *
     * @param stateCode 状态值
     * @param message   返回消息
     * @param body      返回数据体
     */
    public static <T> RespBody<T> build(String stateCode, String message, T body) {
        return new RespBody<>(stateCode, message, body);
    }

}
