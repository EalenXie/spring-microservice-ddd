package com.github.infrastructure.base.resp;


/**
 * @author EalenXie Created on 2020/3/6 17:36.
 * 公共响应码
 */
public enum RespCode implements ResultCode {
    /**
     * Default OK
     */
    OK("200", "OK", "请求成功"),
    /**
     * Default Fail
     */
    FAIL("330", "FAIL", "请求失败"),
    /**
     * BAD_REQUEST
     */
    BAD_REQUEST("400", "Bad Request", "参数无效"),
    /**
     * UNAUTHORIZED
     */
    UNAUTHORIZED("401", "Unauthorized", "未经授权的访问,由于凭据无效被拒绝"),
    /**
     * FORBIDDEN
     */
    FORBIDDEN("403", "Forbidden", "请求资源的访问被服务器拒绝"),
    /**
     * METHOD_NOT_ALLOWED
     */
    METHOD_NOT_ALLOWED("405", "Method Not Allowed", "请求的HTTP方法不允许"),
    /**
     * REQUEST_TIMEOUT
     */
    REQUEST_TIMEOUT("408", "Request Timeout", "请求超时"),
    /**
     * URI_TOO_LONG
     */
    URI_TOO_LONG("414", "URI Too Long", "请求的URL地址长度超限"),
    /**
     * UNSUPPORTED_MEDIA_TYPE
     */
    UNSUPPORTED_MEDIA_TYPE("415", "Unsupported Media Type", "不支持的媒体类型(Content-Type 或 Content-Encoding)"),
    /**
     * TOO_MANY_REQUESTS
     */
    TOO_MANY_REQUESTS("429", "Too Many Requests", "该客户端请求频率过高,请稍后重试"),
    /**
     * MISSING_REQ_HEADER
     */
    MISSING_REQ_HEADER("445", "Missing Request Header", "缺失必要的请求头(Headers)"),
    /**
     * INTERNAL_SERVER_ERROR
     */
    INTERNAL_SERVER_ERROR("500", "Internal Server Error", "服务器内部系统未知异常"),
    /**
     * BAD_GATEWAY
     */
    BAD_GATEWAY("502", "Bad Gateway", "网关异常"),
    /**
     * GATEWAY_TIMEOUT
     */
    GATEWAY_TIMEOUT("504", "Gateway Timeout", "网关超时"),
    /**
     * CONNECT_EXCEPTION
     */
    CONNECT_EXCEPTION("530", "Service Connect Exception", "服务连接异常"),
    /**
     * NULL_POINTER_EXCEPTION
     */
    NULL_POINTER_EXCEPTION("550", "Null Pointer Exception", "服务器内部空指针异常"),
    /**
     * DATABASE_EXCEPTION
     */
    DATABASE_EXCEPTION("551", "Database Exception", "服务器内部数据库发生异常"),
    /**
     * SQL_EXCEPTION
     */
    SQL_EXCEPTION("552", "Sql Exception", "服务器内部数据库SQL执行异常");

    /**
     * 自定义 返回码
     */
    private String code;
    /**
     * 返回码 描述
     */
    private String desc;
    /**
     * 返回码提示说明
     */
    private String message;

    RespCode(String code, String desc, String message) {
        this.code = code;
        this.desc = desc;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
