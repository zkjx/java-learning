package com.hexiang.springmvc.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author: 何翔
 * @date: 2022/5/9
 * @description:
 */

@Repository
public interface StarMapper {

 @Select("select vote from star where id = #{id}")
 Integer getVote(Integer id);

 @Update("update star set vote = vote+1 where id = #{id}")
 Integer updateVote(Integer id);


}
