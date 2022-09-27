package com.zkst.dao;


import com.zkst.domain.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookDao {

    // tb_library 表的逻辑
    @Insert("insert into library_information.tb_library(bookNumber,bookName, author, price,description) values (#{bookNumber},#{bookName},#{author},#{price},#{description})")
    public int save(Book boo1k);

    @Update("update library_information.tb_library set bookName=#{bookName},author=#{author},Price=#{price},description=#{description} where id=#{id}")
    public int update(Book book);

    @Delete("delete from library_information.tb_library where id=#{id}")
    public int  delete(Integer id);

    @Select("select * from library_information.tb_library")
    public List<Book> getAll();

    @Select("select * from library_information.tb_library where id=#{id}")
    public Book getById(Integer id);

    @Select("select * from library_information.tb_library where bookName=#{bookName} ")
    public List<Book> getByBookName(String bookName);

    @Select("select * from library_information.tb_library where bookNumber=#{bookNumber} ")
    public Book getByBookNumber(String bookNumber);

    // borrowing_records 的逻辑
    @Insert("insert into library_information.borrowing_records(bookNumber, bookName, author, description) value (#{bookNumber},#{bookName},#{author},#{price},#{description})")
    public int addBorrowingRecord(Book book);
}
