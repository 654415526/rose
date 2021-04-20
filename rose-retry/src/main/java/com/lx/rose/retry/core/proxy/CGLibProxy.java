package com.lx.rose.retry.core.proxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

/**
 * CJLib 动态代理，需要是父类
 *
 * @author LiXin
 * @version v1.0
 * @date 2021-04-05 20:47
 */
public class CGLibProxy {

    public static <T> T newProxyInstance(Class<T> objClass, Callback callback) {
        return objClass.cast(Enhancer.create(objClass, callback));
    }

}
