package com.hexiang.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author: hexiang
 * @date: 2022/4/25
 * @description:
 */

public class MyAspectXml {

   public Object around(ProceedingJoinPoint pjp) throws Throwable {
       Object[] args = pjp.getArgs();
       System.out.println("口令："+ args[0]);
       return pjp.proceed();
   }

   public void afterAdvice(){
       System.out.println("输入完成");
   }

   public void afterReturningAdvice(){
       System.out.println("请求成功，欢迎");
   }

   public void afterThrowingAdvice(Exception e){
       System.out.println("请求失败，拒绝");
   }
}
