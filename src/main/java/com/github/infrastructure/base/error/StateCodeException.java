package com.github.infrastructure.base.error;


import com.github.infrastructure.base.resp.RespCode;

/**
 * @author EalenXie Created on 2020/2/27 10:16.
 * 自定义运行时 状态码异常 包含一个状态码stateCode 的异常
 */
public class StateCodeException extends RuntimeException {

    private static final RespCode DEFAULT = RespCode.INTERNAL_SERVER_ERROR;

    /**
     * 自定义异常状态码
     */
    private final String stateCode;

    public String getStateCode() {
        return stateCode;
    }

    public StateCodeException() {
        this(DEFAULT.getCode(), DEFAULT.getDesc());
    }

    public StateCodeException(String stateCode) {
        this(stateCode, DEFAULT.getDesc());
    }

    public StateCodeException(String stateCode, String message) {
        this(stateCode, message, null);
    }

    public StateCodeException(String stateCode, String message, Throwable cause) {
        super(message, cause);
        this.stateCode = stateCode;
    }

    /**
     * 是否爬异常堆栈的开关
     *
     * @param writableStackTrace 是否爬取堆栈(已知的无需关注的异常建议设置为true)
     */
    public StateCodeException(boolean writableStackTrace) {
        this(DEFAULT.getCode(), writableStackTrace);
    }

    public StateCodeException(String stateCode, boolean writableStackTrace) {
        this(stateCode, DEFAULT.getDesc(), null, true, writableStackTrace);
    }

    public StateCodeException(String stateCode, String message, boolean writableStackTrace) {
        this(stateCode, message, null, true, writableStackTrace);
    }

    protected StateCodeException(String stateCode, String message, Throwable cause, boolean writableStackTrace) {
        this(stateCode, message, cause, true, writableStackTrace);
    }

    protected StateCodeException(String stateCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.stateCode = stateCode;
    }

}
