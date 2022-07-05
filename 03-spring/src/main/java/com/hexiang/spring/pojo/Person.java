package com.hexiang.spring.pojo;

import com.hexiang.spring.service.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: hexiang
 * @date: 2022/4/11
 * @description:
 */

@Component("person")
public class Person {

  @Value("Zhangsan")
  private String name;

  //@Value("#{dog}")
  @Autowired
  private Pet pet;

  public void keepPet(){
    System.out.println(name+" keeps a pet named "+ pet +", because it can say:");
    pet.say();
  }

  public Person() {
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPet(Pet pet) {
    this.pet = pet;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", pet=" + pet +
        '}';
  }

}
