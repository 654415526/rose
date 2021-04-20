package com.lx.rose.retry.core.annotation;

import com.lx.rose.retry.core.configurable.RetryStartConfigurable;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author LiXin
 * @version v1.0
 * @date 2021-04-06 21:36
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RetryStartConfigurable.class)
@Documented
public @interface EnableRoseRetry {

}
