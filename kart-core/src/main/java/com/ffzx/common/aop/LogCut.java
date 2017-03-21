package com.ffzx.common.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/3/21.
 */
public class LogCut {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Object intecept(ProceedingJoinPoint joinPoint) throws Throwable {


        long t = System.currentTimeMillis();

        Object ret = joinPoint.proceed();
        t = System.currentTimeMillis() - t;
        String methodName=joinPoint.getThis().toString();
        methodName=methodName.substring(0,methodName.indexOf("@"));
        methodName=methodName+"."+joinPoint.getSignature().getName();
        logger.info("method [{}] excuete  {} ms", methodName, t);
        return ret;
    }

}
