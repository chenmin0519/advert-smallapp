package com.advert.smallapp.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.advert.smallapp.basic.controller.*.*(..))")
    public void before(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        logger.info(methodName+"参数----------->:"+ JSONObject.toJSONString(args));
    }
}
