package com.lx.rose.retry.core.configurable;

import com.lx.rose.retry.core.builds.retry.RetryBuild;
import com.lx.rose.retry.core.builds.retry.impl.RetryBuildImpl;
import com.lx.rose.retry.core.enums.RetryModeEnum;

/**
 * @author LiXin
 * @version v1.0
 * @date 2021-04-03 21:40
 */
public class RetryConfigurable {

    /**
     * 重试间隔时间，默认1秒
     */
    private Long intervals = 1L;

    /**
     * 重试次数，默认5次
     */
    private Long retryCount = 5L;

    /**
     * 是否异步执行，默认false
     */
    private Boolean async = false;

    /**
     * 执行模式，默认模板
     *
     * @link : com.simple.lx.rose.core.enums.RetryMode
     */
    private RetryModeEnum retryMode = RetryModeEnum.TEMPLATE;

    /**
     * the static method，build RetryConfigurable instance objects
     *
     * @return RetryBuild
     */
    public static RetryBuild builder() {
        return new RetryBuildImpl();
    }

    public Long getIntervals() {
        return intervals;
    }

    public void setIntervals(Long intervals) {
        this.intervals = intervals;
    }

    public Long getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Long retryCount) {
        this.retryCount = retryCount;
    }

    public Boolean getAsync() {
        return async;
    }

    public void setAsync(Boolean async) {
        this.async = async;
    }

    public RetryModeEnum getRetryMode() {
        return retryMode;
    }

    public void setRetryMode(RetryModeEnum retryMode) {
        this.retryMode = retryMode;
    }
}
