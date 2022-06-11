package com.hexiang.dao;

import com.hexiang.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: hexiang
 * @date: 2022/2/28
 * @description:
 */

public interface StudentDao {
    Integer insertStudent(Student student);
    Integer deleteStudentByStuNum(String stuNum);
    Integer updateStudent(Student student);
    Integer getStudentCount();
    Student getOneByStuNum(String stuNum);
    List<Student> getAllStudent();
    List<Student> getStudentByPage(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
}
