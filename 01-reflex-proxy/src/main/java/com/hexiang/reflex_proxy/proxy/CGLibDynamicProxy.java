package com.hexiang.reflex_proxy.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author: hexiang
 * @date: 2022/3/2
 * @description: CGLib动态代理：是通过创建被代理类的子类来创建代理对象的，因此即使没有实现任何接口的类也可以通过CGlib产生代理对象
 * 1、添加CGLib依赖
 * 2、创建一个类，实现MethodInterceptor接口，同时实现接口中的intercept方法
 * 3、在类中定义一个Object类型的变量，并提供这个变量的有参构造器，用于传递被代理对象
 * 4、定义getProxy方法，创建并返回代理对象（代理对象是通过创建被代理类的子类来创建的）
 */

public class CGLibDynamicProxy implements MethodInterceptor {
    private Object obj;

    public CGLibDynamicProxy(Object obj){
        this.obj = obj;
    }

    public Object getProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();//proxy
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        begin();
        Object returnValue = method.invoke(obj,objects);
        commit();
        return returnValue;
    }

    public void begin(){
        System.out.println("---开始事务---");
    }

    public void commit(){
        System.out.println("---提交事务---");
    }

}
