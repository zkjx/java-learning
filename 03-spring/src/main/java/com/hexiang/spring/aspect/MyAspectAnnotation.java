package com.hexiang.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author: hexiang
 * @date: 2022/4/25
 * @description:
 */

@Component
@Aspect
public class MyAspectAnnotation {

    @Pointcut("execution(* com.hexiang.spring.service.impl.VisitServiceImpl.*(..))")
    public void pt(){}

    @Around("pt()")
   public Object around(ProceedingJoinPoint pjp) throws Throwable {
       Object[] args = pjp.getArgs();
       System.out.println("口令："+ args[0]);
        System.out.println("输入完成");
        return pjp.proceed();

   }


   //@After("pt()")
   //public void afterAdvice(){
   //    System.out.println("输入完成");
   //}



   @AfterReturning("pt()")
   public void afterReturningAdvice(){
       System.out.println("请求成功，欢迎");
   }

   @AfterThrowing(value = "pt()",throwing = "e")
   public void afterThrowingAdvice(Exception e){
       System.out.println("请求失败，拒绝");
   }
}
