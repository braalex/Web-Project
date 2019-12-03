package com.nonsense.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    @Before("execution(* com.nonsense.service.*.*(..))")
    public void beforeServiceMethodInvocation(JoinPoint jp) {
        System.out.println("Invocation of method " + jp.getSignature());
    }

    @Around("execution(* com.nonsense.service.*.*(..))")
    public Object aroundServiceMethodExecution(ProceedingJoinPoint jp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object res = jp.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution of method " + jp.getSignature() +
                " took " + (endTime - startTime) + " ms.");
        return res;
    }
}