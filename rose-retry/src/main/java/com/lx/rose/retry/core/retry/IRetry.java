package com.lx.rose.retry.core.retry;

import com.lx.rose.retry.core.configurable.RetryConfigurable;
import com.lx.rose.retry.core.temptale.AbstractRetry;
import com.lx.rose.retry.core.temptale.AbstractRetryTemplate;

/**
 * @author LiXin
 * @version v1.0
 * @date 2021-04-03 21:44
 */
public class IRetry {

    /**
     * 私有构造器
     */
    private IRetry() {
    }

    /**
     * 静态方法，通过getInstance()方法获取当前实例对象
     *
     * @return the IRetry
     */
    public static IRetry getInstance() {
        return Singleton.SINGLETON;
    }

    /**
     * 静态内部类，在类加载的时候实例化IRetry对象
     */
    private static class Singleton {
        private static final IRetry SINGLETON = new IRetry();
    }

    /**
     * 通过doRetry去执行重试模板，重试参数为默认参数
     * 自定义实现IRetryInterface接口中的doBiz，该方法中的代码发生异常
     * 将会去重试执行，直到retryCount为0则重试结束
     *
     * @param iRetryInterface the iRetryInterface
     * @return the AbstractRetry
     */
    public AbstractRetry doRetry(IRetryInterface iRetryInterface) {
        return new AbstractRetryTemplate(iRetryInterface) {};
    }

    /**
     * 通过doRetry去执行重试模板，重试参数为RetryConfigurable中set的参数
     * 自定义实现IRetryInterface接口中的doBiz，该方法中的代码发生异常
     * 将会去重试执行，直到retryCount为0则重试结束
     *
     * @param iRetryInterface the iRetryInterface
     * @return the AbstractRetry
     */
    public AbstractRetry doRetry(RetryConfigurable retryConfigurable, IRetryInterface iRetryInterface) {
        return new AbstractRetryTemplate(retryConfigurable, iRetryInterface) {};
    }

}
