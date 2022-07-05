package com.hexiang.mybatis.dao;

import com.hexiang.mybatis.pojo.StudentCourseScore;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @author: hexiang
 * @date: 2022/3/28
 * @description:
 */

public interface MultiTableDao {

    List<String> getStudentNameByCourse(String coursename);

    List<StudentCourseScore> getScoreByStudent(Map<String, Object> map);

    @MapKey("getAverageScore")
    List<Map> getAverageScore();
}
