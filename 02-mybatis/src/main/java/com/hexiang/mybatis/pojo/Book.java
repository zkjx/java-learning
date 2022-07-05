package com.hexiang.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: hexiang
 * @date: 2022/3/16
 * @description: Book实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer id;
    private String title;
    private String author;
    //使用注解一定要与表属性一致
    private Date publication_date;
    //不使用注解
    //private Date publicationDate;
    private Integer price;
}
