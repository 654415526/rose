package com.lx.rose.retry.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * JDK 动态代理，obj需要是接口
 *
 * @author LiXin
 * @version v1.0
 * @date 2021-04-05 20:37
 */
public class JDKProxy {

    public static <T> T newProxyInstance(Class<T> objClass, InvocationHandler invocationHandler) {
        return objClass.cast(Proxy.newProxyInstance(objClass.getClassLoader(), new Class[]{objClass}, invocationHandler));
    }

}
