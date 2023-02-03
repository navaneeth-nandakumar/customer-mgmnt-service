package com.cms.customermgmntservice.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {

    @Around("@annotation(Loggable)")
    public Object logAroundAllMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        String methodName = joinPoint.getSignature().getName();
        logger.info("Enter : {}", methodName);
        Object returnValue = null;
        try {
            returnValue = joinPoint.proceed();
        } catch (Throwable ex) {
            logger.error("Exception occured : {}",methodName);
            throw ex;
        }
        logger.info("Exit : {}", methodName);
        return returnValue;
    }

}
