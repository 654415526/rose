package com.lx.rose.retry.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author LiXin
 * @version v1.0
 * @date 2021-04-08 20:39
 */
public class ApplicationContextUtils implements ApplicationContextAware {

    private ApplicationContext applicationContext = null;

    /**
     * get applicationContext
     * @param applicationContext the applicationContext
     * @throws BeansException the BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * @param beanName the bean name
     * @return obj
     */
    public Object getBean(String beanName) {
        return this.applicationContext.getBean(beanName);
    }

    /**
     * @return the ApplicationContext
     */
    public ApplicationContext getApplicationContext() {
        return this.applicationContext;
    }
}
