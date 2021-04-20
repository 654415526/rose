package com.lx.rose.retry.core.annotation;


import java.lang.annotation.*;

/**
 * @author LiXin
 * @version v1.0
 * @date 2021-04-05 17:11
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedRetry {

    long intervals() default 1L;

    long retryCount() default 5L;

    boolean async() default false;

}
