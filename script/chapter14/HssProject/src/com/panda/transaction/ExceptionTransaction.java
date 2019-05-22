package com.panda.transaction;

import com.opensymphony.xwork2.interceptor.annotations.Before;
import com.panda.util.Log4jUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * USER:MrC.
 * DATE:2017/3/28.
 * TIME:9:50.
 */
public class ExceptionTransaction {
    /*@Pointcut("execution(* com.panda.serviceImpl.*(..))")//serviceImpl包下的所有方法
    private void aa(){}//方法签名*/
    //异常通知
    public void throwException(JoinPoint joinPoint ,Throwable e) {
        Class className = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("出现异常的地方:"+className+methodName+e.getMessage());
    }
}
