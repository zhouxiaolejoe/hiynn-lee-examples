package com.hiynn.dynamic.datasource.datasource;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
* @Description  AOP切面
* @Author ZhouXiaoLe
* @Date  2019/7/16  13:28
* @Param 
* @return 
**/
@Aspect
@Component
@Order(1)
@Slf4j
public class DataSourceAsepct {

    //加载数据源(切入类和方法、@annotation只能作用于方法)
//    @Pointcut("@annotation(com.dynamic.datasource.datasource.DataSource)")
    @Pointcut("execution(public * com.hiynn.dynamic.datasource.service.*.*(..))")
    public void pointCut(){ }
	/**
	* @Description (获取目标类和目标方法)
	* @Author ZhouXiaoLe
	* @Date  2019/7/17  12:52
	* @Param [joinPoint]
	* @return void
	**/
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
	    Class<?> target = joinPoint.getTarget().getClass();
	    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
	    for (Class<?> clazz : target.getInterfaces()) {
		    resolveDataSource(clazz, signature.getMethod());
	    }
    }
    /**
    * @Description  1. 默认使用类型注解 2.方法注解覆盖，以方法注解为最后值
    * @Author ZhouXiaoLe
    * @Date  2019/7/17  12:46
    * @Param [clazz, method]
    * @return void
    **/
	private void resolveDataSource(Class<?> clazz, Method method) {
		try {
			Class<?>[] classes = method.getParameterTypes();
			DataSource dataSource =null;
			if (clazz.isAnnotationPresent(DataSource.class)) {
				dataSource = clazz.getAnnotation(DataSource.class);
			}
			Method m = clazz.getMethod(method.getName(), classes);
			if (m != null && m.isAnnotationPresent(DataSource.class)) {
				dataSource = m.getAnnotation(DataSource.class);
			}
			DataSourceHolder.setDataSource(dataSource.value());
			log.info("Aop Switch data sources: [" + dataSource.value()+"]");
		} catch (Exception e) {
			log.error(clazz + ":" + e.getMessage());
		}
	}


    @After("pointCut()")
    public void after() {
        DataSourceHolder.clearDataSource();
    }
}
