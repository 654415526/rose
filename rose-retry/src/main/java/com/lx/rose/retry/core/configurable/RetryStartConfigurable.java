package com.lx.rose.retry.core.configurable;

import com.lx.rose.retry.core.annotation.NeedRetry;
import com.lx.rose.retry.core.exception.retry.RetryException;
import com.lx.rose.retry.core.proxy.CGLibProxy;
import com.lx.rose.retry.core.retry.IRetry;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;

/**
 * the retry start configurable
 *
 * @author LiXin
 * @version v1.0
 * @date 2021-04-06 22:06
 */
public class RetryStartConfigurable implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext = null;

    /**
     * get applicationContext
     *
     * @param applicationContext the applicationContext
     * @throws BeansException the BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 通过beanName 获取 bean
     *
     * @param beanName the beanName
     * @return the obj
     */
    private Object getBeanByName(String beanName) {
        return this.applicationContext.getBean(beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Method[] methods = beanClass.getMethods();
        for (Method method : methods) {
            NeedRetry needRetry = method.getAnnotation(NeedRetry.class);
            if (null != needRetry) {
                return CGLibProxy.newProxyInstance(beanClass, (MethodInterceptor) (object, method1, args, methodProxy) -> doTemplate(buildRetryConfigurable(needRetry), object, args, methodProxy));
            }
        }
        return bean;
    }

    /**
     * retry run doTemplate
     *
     * @param retryConfigurable the retryConfigurable
     * @param object            the object
     * @param args              the args
     * @param method            the method
     * @return the obj
     * @throws RetryException       the RetryException
     * @throws InterruptedException the InterruptedException
     */
    private Object doTemplate(RetryConfigurable retryConfigurable, Object object, Object[] args, MethodProxy method) throws RetryException, InterruptedException {
        final Object[] result = {null};
        IRetry.getInstance().doRetry(retryConfigurable, () -> {
            try {
                result[0] = method.invokeSuper(object, args);
            } catch (Throwable throwable) {
                throw new RetryException(throwable.getMessage(), throwable.getCause());
            }
            return result[0];
        }).execute();
        return result[0];
    }

    /**
     * 获取注解上的属性build到RetryConfigurable对象中
     *
     * @param needRetry the needRetry
     * @return the RetryConfigurable
     */
    private RetryConfigurable buildRetryConfigurable(NeedRetry needRetry) {
        return RetryConfigurable.builder()
                .intervals(needRetry.intervals())
                .async(needRetry.async())
                .retryCount(needRetry.retryCount()).build();
    }

}
