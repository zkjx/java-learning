package com.hexiang.reflex_proxy;

import com.hexiang.reflex_proxy.dao.StudentDao;
import com.hexiang.reflex_proxy.proxy.CGLibDynamicProxy;
import com.hexiang.reflex_proxy.proxy.JDKDynamicProxy;
import com.hexiang.reflex_proxy.proxy.StaticProxy;
import com.hexiang.reflex_proxy.service.IGeneralService;
import com.hexiang.reflex_proxy.service.impl.StudentServiceImpl;
import org.junit.Test;

/**
 * @author: hexiang
 * @date: 2022/3/2
 * @description: 代理模式的好处
 * 1、被代理类中只用关心核心业务的实现，将通用的管理型逻辑（事务逻辑、日志管理）和业务逻辑分离
 * 2、将通用的代码放在代理类中实现，提供了代码的复用性
 * 3、通过在代理类添加业务逻辑，实现对原有业务逻辑的拓展
 */

public class ProxyTest {

    @Test
    public void testJDKDynamicProxy(){
        /*
        * @Author: 何翔
        * @Description: JDK动态代理：几乎可以为大部分的类产生代理对象
        * @DateTime: 2022/3/2 15:14
        * @Params: []
        * @Return void
        */

        //创建被代理对象
        StudentServiceImpl studentService = new StudentServiceImpl(new StudentDao());
        //创建动态代理对象，并将被代理对象传递到代理类中赋值给obj
        JDKDynamicProxy jdkDynamicProxy = new JDKDynamicProxy(studentService);
        //这里的proxy就是产生的代理对象,它可以强转成为被代理对象实现的接口类型
        IGeneralService proxy = (IGeneralService)jdkDynamicProxy.getProxy();
        //使用代理对象调用方法，不会直接进入到被代理类（并不会执行调用的方法，而是进入到创建代理对象时指定的InvocationHandler类中的invoke方法）
        //调用的方法作为一个Method参数，传递给了invoke方法
        proxy.delete();
    }

    @Test
    public void testCGLibDynamicProxy(){
        /*
        * @Author: 何翔
        * @Description: CGLib动态代理
        * 由于JDK动态代理是通过被代理类实现的接口来创建代理对象的，因此JDK动态代理只能代理实现了接口的类的对
          象。但如果一个类没有买现任何接口，就需要使用其他（此）方式产生代理对象，但是不能为final类创建代理对象
        * 运行配置需要加上：--add-opens java.base/java.lang=ALL-UNNAMED
        * @DateTime: 2022/3/2 20:06
        * @Params: []
        * @Return void
        */

        //创建被代理对象
        StudentServiceImpl studentService = new StudentServiceImpl(new StudentDao());
        //通过cglib动态代理类创建代理对象，并将被代理对象传递到代理类中赋值给obj
        CGLibDynamicProxy cgLibDynamicProxy = new CGLibDynamicProxy(studentService);
        //代理对象实际上是被代理对象子类，因此代理对象可直接强转为被代理类类型
        StudentServiceImpl proxy = (StudentServiceImpl)cgLibDynamicProxy.getProxy();
        //使用代理对象调用方法，实际上并没有执行这个方法，而是执行了代理类中的intercept方法，将当前调用的方法以及方法中的参数传递到了intercept方法
        proxy.update();

    }

    @Test
    public void testStaticProxy(){
        /*
        * @Author: 何翔
        * @Description: 静态代理：代理类只能够为特定的类生产代理对象，不能代理任意类
        * @DateTime: 2022/3/2 14:19
        * @Params: []
        * @Return void
        */
     StaticProxy staticProxy = new StaticProxy(new StudentServiceImpl(new StudentDao()));
     staticProxy.insert();
    }
}
