package com.hexiang.spring;

import com.hexiang.spring.pojo.Person;
import com.hexiang.spring.service.TBookService;
import com.hexiang.spring.service.VisitService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: hexiang
 * @date: 2022/4/12
 * @description:
 */

public class SpringTests {

    @Test
    public void testTransactionAnnotation(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        TBookService tBookService = context.getBean("bookServiceImpl", TBookService.class);
        tBookService.getBookList().forEach(System.out::println);
        System.out.println("开始销售》》》》》》》》》》》》》》》》》");
        System.out.println(tBookService.sellBook("库存","售出",20));
        System.out.println("销售结束》》》》》》》》》》》》》》》》》");
        tBookService.getBookList().forEach(System.out::println);
    }

    @Test
    public void testTransactionXml(){
        ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        TBookService tBookService = context.getBean("tBookService", TBookService.class);
        tBookService.getBookList().forEach(System.out::println);
        System.out.println("开始销售》》》》》》》》》》》》》》》》》");
        System.out.println(tBookService.sellBook("库存","售出",20));
        System.out.println("销售结束》》》》》》》》》》》》》》》》》");
        tBookService.getBookList().forEach(System.out::println);
    }

    @Test
    public void testAopXml(){
        ClassPathXmlApplicationContext context = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        VisitService visitService = context.getBean("visitService", VisitService.class);
        try {
            visitService.visit("agree");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            visitService.visit("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAopAnnotation(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        VisitService visitService = context.getBean("visitServiceImpl", VisitService.class);
        try {
            visitService.visit("agree");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            visitService.visit("ok");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testIocXml() {
     //1.初始化Spring容器,加载Spring配置⽂件
     ClassPathXmlApplicationContext context = new
             ClassPathXmlApplicationContext("applicationContext.xml");
     //2.获取bean对象
     Person person = (Person) context.getBean("person");
     //3.打印bean对象
     person.keepPet();
    }

    @Test
    public void testIocAnnotation(){
     AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
     Person person = (Person) context.getBean("person");
     person.keepPet();
    }


}
