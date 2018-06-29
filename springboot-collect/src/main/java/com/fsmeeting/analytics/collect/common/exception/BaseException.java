/*
 * 文件名：BaseException.java
 * 版权：Copyright by www.fsmeeting.com
 * 描述： 公共异常类
 * 修改人：zhangxt
 * 修改时间：2017年7月15日
 * 修改内容：
 */

package com.fsmeeting.analytics.collect.common.exception;

/**
 * 公共异常类，包含code
 *
 * @author zhangxt
 * @version 2017年7月15日
 * @see BaseException
 */

public class BaseException extends RuntimeException {


    /**
     * serialVersionUID<br>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    private int code;

    public BaseException() {
        super();
    }

    /**
     * @param message 异常
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * @param message 错误码
     * @param cause   异常
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param code    错误码
     * @param message 信息
     */
    public BaseException(int code, String message) {
        super(message);

        this.code = code;
    }

    /**
     * @param code    错误码
     * @param message 信息
     * @param cause   异常
     */
    public BaseException(int code, String message, Throwable cause) {
        super(message, cause);

        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
