package com.hexiang.spring.pojo;


import com.hexiang.spring.service.Pet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: hexiang
 * @date: 2022/4/12
 * @description:
 */

@Component("dog")
public class Dog implements Pet {

    @Value("WangWang")
     private String name;

     public Dog() {
     }

 public void setName(String name) {
      this.name = name;
     }

     @Override
     public String toString() {
      return name;
     }

     @Override
     public void say() {
           System.out.println("WangWang");
     }
}
