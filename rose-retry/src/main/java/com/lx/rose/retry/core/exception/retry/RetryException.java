package com.lx.rose.retry.core.exception.retry;

import com.lx.rose.retry.core.exception.HandlerException;

/**
 * @author LiXin
 * @version v1.0
 * @date 2021-04-03 21:29
 */
public class RetryException extends HandlerException {

    public RetryException(String message) {
        super(message);
    }

    public RetryException(String message, Throwable cause) {
        super(message, cause);
    }
}
