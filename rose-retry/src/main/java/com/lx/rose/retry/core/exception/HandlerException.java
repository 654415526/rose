package com.lx.rose.retry.core.exception;

/**
 * @author LiXin
 * @version v1.0
 * @date 2021-04-03 21:29
 */
public abstract class HandlerException extends Throwable{

    public HandlerException(String message) {
        super(message);
    }

    public HandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
