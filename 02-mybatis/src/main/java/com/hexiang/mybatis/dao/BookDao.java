package com.hexiang.mybatis.dao;

import com.hexiang.mybatis.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: hexiang
 * @date: 2022/3/16
 * @description: Book类接口
 */

public interface BookDao {

    Integer insert(Book book);
    Integer update(Book book);
    Integer delete(Integer id);
    Integer getBookCount();
    Book getOneBook(Integer id);
    List<Book> getAllBook();
    List<Book> getBookByPage(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
}
