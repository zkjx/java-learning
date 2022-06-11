package com.hexiang.reflex_proxy;

import com.hexiang.reflex_proxy.pojo.Student;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: hexiang
 * @date: 2022/3/1
 * @description:
 */

public class ReflexTest {

    @Test
    public void testGetClassName() throws ClassNotFoundException {
        //1.Class类中的静态方法forName("全类名")
        //全类名:包名 + 类名
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        System.out.println(clazz);

        //2.通过class属性来获取
        Class clazz2 = Student.class;
        System.out.println(clazz2);

        //3.利用对象的getClass方法来获取class对象
        //getClass方法是定义在Object类中.
        Student s = new Student();
        Class clazz3 = s.getClass();
        System.out.println(clazz3);

        System.out.println(clazz == clazz2);
        System.out.println(clazz2 == clazz3);
    }

    @Test
    public void testGetConstructors() throws ClassNotFoundException {
        // //Constructor<?>[] getConstructors()：返回公共的构造方法对象的数组
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
    }

    @Test
    public void testGetDeclaredConstructors() throws ClassNotFoundException {
        //Constructor<?>[] getDeclaredConstructors()：返回所有构造方法对象的数组
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
    }

    @Test
    public void testGetConstructor() throws ClassNotFoundException, NoSuchMethodException {
        //Constructor<T> getConstructor(Class<?>... parameterTypes)：返回单个公共构造方法对象
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        //小括号中,一定要跟构造方法的形参保持一致.
        Constructor constructor1 = clazz.getConstructor();
        System.out.println(constructor1);

        Constructor constructor2 = clazz.getConstructor(String.class, Integer.class);
        System.out.println(constructor2);

        //因为Student类中,没有只有一个Integer的构造,所以这里会报错.
        //Constructor constructor3 = clazz.getConstructor(Integer.class);
        //System.out.println(constructor3);
    }

    @Test
    public void testGetDeclaredConstructor() throws ClassNotFoundException, NoSuchMethodException {
        //Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)：返回单个构造方法对象
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        Constructor constructor = clazz.getDeclaredConstructor(String.class);
        System.out.println(constructor);
    }

    @Test
    public void testNewInstance() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //T newInstance(Object... initargs)：根据指定的构造方法创建对象
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        Constructor constructor = clazz.getConstructor(String.class,Integer.class);
        Student student = (Student)constructor.newInstance("hexiang", 21);
        System.out.println(student);
    }

    @Test
    public void testSetAccessible() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //获取一个私有的构造方法并创建对象
        //1.获取class对象
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        //2.获取一个私有化的构造方法.
        Constructor constructor = clazz.getDeclaredConstructor(String.class);
        //被private修饰的成员,不能直接使用的
        //如果用反射强行获取并使用,需要临时取消访问检查
        constructor.setAccessible(true);
        //3.直接创建对象
        Student student = (Student) constructor.newInstance("hexiang");
        System.out.println(student);
    }

    @Test
    public void testGetFields() throws ClassNotFoundException {
        //Field[] getFields()：返回所有公共成员变量对象的数组
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
    }

    @Test
    public void testGetDeclaredFields() throws ClassNotFoundException {
        //Field[] getDeclaredFields()：返回所有成员变量对象的数组
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
    }

    @Test
    public void testGetField() throws ClassNotFoundException, NoSuchFieldException {
        //Field getField(String name)：返回单个公共成员变量对象
        //想要获取的成员变量必须是真实存在的
        //且必须是public修饰的.
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        Field field = clazz.getField("name");
        System.out.println(field);
    }

    @Test
    public void testGetDeclaredField() throws ClassNotFoundException, NoSuchFieldException {
        //Field getDeclaredField(String name)：返回单个成员变量对象
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        Field field = clazz.getDeclaredField("age");
        System.out.println(field);
    }

    @Test
    public void testFieldSet() throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        //void set(Object obj, Object value)：给obj对象的成员变量赋值为value
        //1.获取class对象
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        //2.获取name这个Field对象
        Field field = clazz.getField("name");
        //3.利用set方法进行赋值.
        //3.1先创建一个Student对象
        Student student = (Student) clazz.newInstance();
        //3.2有了对象才可以给指定对象进行赋值
        field.set(student,"hexiang");
        System.out.println(student);
    }

    @Test
    public void testFieldGet() throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        //1.获取class对象
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        //2.获取成员变量Field的对象
        Field field = clazz.getDeclaredField("age");
        //3.取消一下访问检查
        field.setAccessible(true);
        //4.调用get方法来获取值
        //4.1创建一个对象
        Student student = (Student) clazz.newInstance();
        //4.2获取指定对象的的值
        Object o = field.get(student);
        //5.打印一下
        System.out.println(o);
    }

    @Test
    public void testGetMethods() throws ClassNotFoundException {
        //Method[] getMethods()：返回所有公共成员方法对象的数组，包括继承的
        //1.获取class对象
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        //2.获取成员方法对象
        Method[] methods = clazz.getMethods();
        //3.遍历
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    @Test
    public void testGetDeclaredMethods() throws ClassNotFoundException {
        //Method[] getDeclaredMethods()：返回所有成员方法对象的数组，不包括继承的
        //1.获取class对象
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        //2.获取Method对象
        Method[] methods = clazz.getDeclaredMethods();
        //3.遍历一下数组
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    @Test
    public void testGetMethod() throws ClassNotFoundException, NoSuchMethodException {
        //Method getMethod(String name, Class<?>... parameterTypes) ：返回单个公共成员方法对象
        //1.获取class对象
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        //2.获取Method对象
        Method method = clazz.getMethod("function1");
        Method method2 = clazz.getMethod("function2",String.class);
        System.out.println(method);
        System.out.println(method2);
    }

    @Test
    public void testGetDeclaredMethod() throws ClassNotFoundException, NoSuchMethodException {
        //Method getDeclaredMethod(String name, Class<?>... parameterTypes)：返回单个成员方法对象
        //1.获取class对象
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        //2.获取一个成员方法show
        Method method = clazz.getDeclaredMethod("show");
        //3.打印一下
        System.out.println(method);
    }

    @Test
    public void testInvoke() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        //Object invoke(Object obj, Object... args)：运行方法
        //参数一：用obj对象调用该方法
        //参数二：调用方法的传递的参数（如果没有就不写）
        //返回值：方法的返回值（如果没有就不写）

        //1.获取class对象
        Class clazz = Class.forName("com.hexiang.reflex_proxy.pojo.Student");
        //2.获取里面的Method对象  function4
        Method method = clazz.getMethod("function4", String.class);
        //3.运行function4方法就可以了
        //3.1创建一个Student对象,当做方法的调用者
        Student student = (Student) clazz.newInstance();
        //3.2运行方法
        Object result = method.invoke(student, "hexiang");
        //4.打印一下返回值
        System.out.println(result);
    }



}