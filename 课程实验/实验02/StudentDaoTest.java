package com.hexiang.dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hexiang.pojo.Student;
import com.hexiang.utils.MybatisUtil;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * @author: hexiang
 * @date: 2022/2/28
 * @description:
 */

public class StudentDaoTest {

    @Test
    public void testInsertStudent()  {
            Student student =new Student(null, "04191318", "wenbin", "男", 24);
            Integer result = MybatisUtil.getMapper(StudentDao.class,true).insertStudent(student);
            assertEquals(1,(int)result);
            //主键回传检验
            System.out.println(student.getStuId());
    }

    @Test
    public void testDeleteStudentByStuNum() {
        Integer result = MybatisUtil.getMapper(StudentDao.class).deleteStudentByStuNum("04191318");
        MybatisUtil.getSqlSession().commit();
        assertEquals(1,(int)result);
    }

    @Test
    public void testUpdateStudentByStuNum(){
        Integer result = MybatisUtil.getMapper(StudentDao.class).updateStudent(new Student(null, "04191315", "hexiang", "男", 20));
        assertEquals(1,(int)result);
    }

    @Test
    public void testGetAllStudent(){
        List<Student> students = MybatisUtil.getMapper(StudentDao.class).getAllStudent();
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testGetOneByStuNum(){
        Student student = MybatisUtil.getMapper(StudentDao.class).getOneByStuNum("04191315");
        System.out.println(student);
    }



    @Test
    public void testGetStudentCount(){
       Integer result = MybatisUtil.getMapper(StudentDao.class).getStudentCount();
        System.out.println(result);
    }

    @Test
    public void testGetStudentByPage(){
        List<Student> students = MybatisUtil.getMapper(StudentDao.class).getStudentByPage(0,2);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testGetStudentByPageHelper(){
        PageHelper.startPage(2,2);
        List<Student> students = MybatisUtil.getMapper(StudentDao.class).getAllStudent();
        PageInfo<Student> pageInfo = new PageInfo<>(students);
        List<Student> list = pageInfo.getList();
        for (Student student : list) {
            System.out.println(student);
        }

    }


}