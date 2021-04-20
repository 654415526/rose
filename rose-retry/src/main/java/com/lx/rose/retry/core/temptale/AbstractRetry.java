package com.lx.rose.retry.core.temptale;


import com.lx.rose.retry.core.callback.AbstractCallBack;
import com.lx.rose.retry.core.exception.retry.RetryException;

/**
 * the abstract retry,It is an abstract class
 *
 * @author LiXin
 * @version v1.0
 * @date 2021-04-03 21:25
 */
public abstract class AbstractRetry {

    /**
     * the abstract method execute(),not need call back use
     *
     * @throws RetryException       the retry exception
     * @throws InterruptedException the interrupted exception
     */
    public abstract void execute() throws RetryException, InterruptedException;

    /**
     * the abstract method execute(), need call back use
     *
     * @param abstractCallBack the abstract call back
     * @throws RetryException       the retry exception
     * @throws InterruptedException the interrupted exception
     */
    public abstract void execute(AbstractCallBack abstractCallBack) throws RetryException, InterruptedException;
}
