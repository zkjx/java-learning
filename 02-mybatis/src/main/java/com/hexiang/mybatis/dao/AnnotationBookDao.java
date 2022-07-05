package com.hexiang.mybatis.dao;

import com.hexiang.mybatis.pojo.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: hexiang
 * @date: 2022/3/28
 * @description:
 */

public interface AnnotationBookDao {

    @Insert("insert into tb_book(title, author, publication_date, price) values(#{title},#{author},#{publication_date},#{price})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(Book book);

    @Update("update tb_book set title=#{title},author=#{author},publication_date=#{publication_date},price=#{price} where id=#{id}")
    Integer update(Book book);

    @Delete("delete from tb_book where id=#{id}")
    Integer delete(Integer id);

    @Select("select count(1) from tb_book")
    Integer getBookCount();

    @Select("""
            select id,
                    title,
                    author,
                    publication_date,
                    price from tb_book where id=#{id}""")
    Book getOneBook(Integer id);

    @Select("""
            select id,
                    title,
                    author,
                    publication_date,
                    price from tb_book""")
    List<Book> getAllBook();

    @Select("""
            select id,
                    title,
                    author,
                    publication_date,
                    price from tb_book limit #{start}, #{pageSize}""")
    List<Book> getBookByPage(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
}
