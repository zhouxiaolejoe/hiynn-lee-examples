package com.hiynn.spring.security.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Description
 * @Project hiynn-lee-example1
 * @Package com.hiynn.spring.security.aspect
 * @Author ZhouXiaoLe
 * @Date 2019-07-24 10:34
 */
//@Aspect
@Component
@Slf4j
public class TimeAspect {

    @Around("execution(* com.hiynn.spring.security.controller.*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("time aspect start");
        long start = System.currentTimeMillis();
        Object proceed = point.proceed();
        Object[] args = point.getArgs();
        Arrays.stream(args).forEach(arg -> log.info(String.format("%s参数:%s", point.getSignature().getName(), arg)));
        log.info(String.format("接口耗时:%s", System.currentTimeMillis() - start));
        log.info("time aspect finish");
        return proceed;
    }
}
