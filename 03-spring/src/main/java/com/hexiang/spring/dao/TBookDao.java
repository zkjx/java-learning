package com.hexiang.spring.dao;

import com.hexiang.spring.pojo.TBook;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* @author hexiang
* @description 针对表【t_book】的数据库操作Mapper
* @createDate 2022-04-25 14:24:48
* @Entity com.hexiang.spring.beans.TBook
*/

@Repository
public interface TBookDao  {

    @Select("select * from t_book")
    List<TBook> getBookList();

    @Update("update t_book set num=#{num} where id=#{id}")
    void updateBook(TBook book);

    @Select("Select * from t_book where status=#{name}")
    TBook findBookByName(String name);


}




