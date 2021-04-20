package com.lx.rose.retry.core.retry;

import com.lx.rose.retry.core.exception.retry.RetryException;

/**
 * @author LiXin
 * @version v1.0
 * @date 2021-04-13 00:38
 */
@FunctionalInterface
public interface IRetryInterface {

    /**
     * the method doBiz()
     * Please customize the business retry logic
     *
     * @return object
     * @throws RetryException the retry exception
     */
    Object doBiz() throws RetryException;

}
