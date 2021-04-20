package com.lx.rose.retry.core.builds.retry.impl;

import com.lx.rose.retry.core.builds.retry.RetryBuild;
import com.lx.rose.retry.core.configurable.RetryConfigurable;
import com.lx.rose.retry.core.enums.RetryModeEnum;

/**
 * @author LiXin
 * @version v1.0
 * @date 2021-04-04 16:30
 */
public class RetryBuildImpl implements RetryBuild {

    private RetryConfigurable retryConfigurable = new RetryConfigurable();

    @Override
    public RetryBuild intervals(Long intervals) {
        retryConfigurable.setIntervals(intervals);
        return this;
    }

    @Override
    public RetryBuild retryCount(Long retryCount) {
        retryConfigurable.setRetryCount(retryCount);
        return this;
    }

    @Override
    public RetryBuild async(Boolean async) {
        retryConfigurable.setAsync(async);
        return this;
    }

    @Override
    public RetryBuild retryMode(RetryModeEnum retryMode) {
        retryConfigurable.setRetryMode(retryMode);
        return this;
    }

    @Override
    public RetryConfigurable build() {
        return this.retryConfigurable;
    }
}
