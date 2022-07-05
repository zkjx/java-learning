package com.hexiang.springmvc.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: 何翔
 * @date: 2022/5/10
 * @description:
 */

@Repository
public interface BookMapper {

    @Update("update book set num = num - #{num} where status = '库存'")
    void store(Integer num);

    @Update("update book set num = num + #{num} where status = '售出'")
    void sale(Integer num);

    @Select("select num from book")
    List<Integer> getBookNum();

}
