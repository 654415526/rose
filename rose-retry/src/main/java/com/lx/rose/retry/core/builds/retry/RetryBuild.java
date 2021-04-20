package com.lx.rose.retry.core.builds.retry;

import com.lx.rose.retry.core.configurable.RetryConfigurable;
import com.lx.rose.retry.core.enums.RetryModeEnum;

/**
 * @author LiXin
 * @version v1.0
 * @date 2021-04-04 16:23
 */
public interface RetryBuild {

    RetryBuild intervals(Long intervals);

    RetryBuild retryCount(Long retryCount);

    RetryBuild async(Boolean async);

    RetryBuild retryMode(RetryModeEnum retryMode);

    RetryConfigurable build();

}
