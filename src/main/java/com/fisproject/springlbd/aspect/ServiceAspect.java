package com.fisproject.springlbd.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    private final Logger LOG = LoggerFactory.getLogger(ServiceAspect.class);

    // .. - any number of params https://stackoverflow.com/questions/15660535/get-method-arguments-using-spring-aop
    @Before("execution(* com.fisproject.springlbd.service.SprintServiceImpl.*(..))")
    public void before(JoinPoint joinPoint) {
        LOG.info("ServiceAspect Before (params):");
        for (Object obj : joinPoint.getArgs())
            LOG.info("\t{}", obj.toString());
    }

    // https://stackoverflow.com/questions/18469703/log-the-return-value-of-a-method-using-spring-aop
    @AfterReturning(pointcut = "execution(* com.fisproject.springlbd.service.SprintServiceImpl.*(..))", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        LOG.info("ServiceAspect After (result):");
        LOG.info("\t{}", result.toString());
    }

}
