package com.lx.rose.retry.core.callback;

/**
 * @author LiXin
 * @version v1.0
 * @date 2021-04-03 20:56
 */
public abstract class AbstractCallBack {

    /**
     * the on success
     *
     * @param <T> the t
     * @param var the var
     */
    public abstract <T> void onSuccess(T var);

    /**
     * the on failure
     *
     * @param throwable the throwable
     */
    public abstract void onFailure(Throwable throwable);

}
