package com.hexiang.spring.service;

import com.hexiang.spring.pojo.TBook;

import java.util.List;

/**
* @author hexiang
* @description 针对表【t_book】的数据库操作Service
* @createDate 2022-04-25 14:24:48
*/


public interface TBookService  {

    List<TBook> getBookList();

    String  sellBook(String storeName,String sellName,Integer sellNum);
}
