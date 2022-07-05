package com.hexiang.reflex_proxy.pojo;

import lombok.Data;

/**
 * @author: hexiang
 * @date: 2022/3/1
 * @description:
 */

@Data
public class Student {
    public String name ;
    private Integer age  ;
    public String gender ;

    public Student(){}

    private Student(String name){
        this.name = name;
    }

    public Student(String name ,Integer age){
        this.name = name;this.age = age;
        System.out.println("My name is "+name+" and I'm "+age+" years old!");
    }

    public void study(){
        System.out.println("学生在学习");
    }

    private void show() {
        System.out.println("私有的show方法，无参无返回值");
    }

    //公共的，无参无返回值
    public void function1() {
        System.out.println("function1方法，无参无返回值");
    }

    //公共的，有参无返回值
    public void function2(String name) {
        System.out.println("function2方法，有参无返回值,参数为" + name);
    }

    //公共的，无参有返回值
    public String function3() {
        return "function3方法，无参有返回值";
    }

    //公共的，有参有返回值
    public String function4(String name) {
        return "function4方法，有参有返回值,参数为" + name;
    }

}
