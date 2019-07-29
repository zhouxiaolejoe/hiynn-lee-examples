package com.hiynn.dynamic.datasource.config.datasource;

import java.lang.annotation.*;

/**
 * @Description 注解
 * @Author ZhouXiaoLe
 * @Date 2019/7/16  12:05
 * @Param
 * @return
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface DS {
    String value() default "";
}
