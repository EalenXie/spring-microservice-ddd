package com.github.infrastructure.base.resp;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author EalenXie Created on 2020/3/2 13:15.
 * 当发生错误或异常时的对象体
 * 提供基础错误返回对象支持
 */
public class ErrorBody {

    /**
     * 异常类名
     */
    private String throwable;
    /**
     * 异常抛出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS", timezone = "GMT+8")
    private Date throwTime;
    /**
     * 异常消息
     */
    private String message;
    /**
     * 异常堆栈
     */
    private String stackTrace;
    /**
     * 异常源数据
     */
    private Map<String, Object> metadata;

    public String getThrowable() {
        return throwable;
    }

    public void setThrowable(String throwable) {
        this.throwable = throwable;
    }

    public Date getThrowTime() {
        return throwTime;
    }

    public void setThrowTime(Date throwTime) {
        this.throwTime = throwTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public ErrorBody(String throwable, Date throwTime, String message, String stackTrace, Map<String, Object> metadata) {
        this.throwable = throwable;
        this.throwTime = throwTime;
        this.message = message;
        this.stackTrace = stackTrace;
        this.metadata = metadata;
    }

    /**
     * 获取ErrorData通过 Throwable 默认不收集堆栈信息
     */
    public static ErrorBody build(Throwable e) {
        return build(e, false);
    }

    /**
     * 获取ErrorData通过 Throwable
     *
     * @param e          异常
     * @param stackTrace 是否收集堆栈
     */
    public static ErrorBody build(Throwable e, boolean stackTrace) {
        return new ErrorBody(
                e.getClass().getName(), new Date(),
                e.getMessage(),
                stackTrace ? collectStackTrace(e) : null,
                new TreeMap<>());
    }

    /**
     * 收集异常堆栈信息
     */
    private static String collectStackTrace(Throwable e) {
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw, true)) {
            e.printStackTrace(pw);
            return sw.toString();
        } catch (IOException ex) {
            return "collectStackTrace exception : " + ex.getMessage();
        }
    }

}
