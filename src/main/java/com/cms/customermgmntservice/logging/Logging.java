package com.cms.customermgmntservice.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logging {

    @Around("@annotation(Loggable)")
    public Object logAroundAllMethods(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Enter : " + methodName);
        Object returnValue = null;
        try {
            returnValue = joinPoint.proceed();
        } catch (Throwable ex) {
            System.out.println("Exception occured: " + methodName);
        }
        System.out.println("Exit : " + methodName);
        return returnValue;
    }

}
