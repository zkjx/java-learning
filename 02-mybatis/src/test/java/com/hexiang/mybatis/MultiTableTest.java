package com.hexiang.mybatis;

import com.hexiang.mybatis.dao.MultiTableDao;
import com.hexiang.mybatis.utils.MybatisUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hexiang
 * @date: 2022/3/28
 * @description:
 * 1.查询选修了数据库课程的所有同学的姓名
 * 2.查询根据模糊姓名（比如姓王的）、性别的条件组合查询成绩（提示：可使用动态SQL)。
 * 3.统计出各门课的平均成绩并从高到低排序输出。
 */

public class MultiTableTest {

    MultiTableDao multiTableDao = MybatisUtil.getMapper(MultiTableDao.class,true);

    @Test
    public void testGetStudentNameByCourse() {
        //1.查询选修了数据库课程的所有同学的姓名
        System.out.println(multiTableDao.getStudentNameByCourse("Java语言"));
    }

    @Test
    public void testGetStudentScoreByCondition() {
        //2.查询根据模糊姓名（比如姓王的）、性别的条件组合查询成绩
        Map<String, Object> map = new HashMap<>();
        map.put("name", "王");
        //map.put("sex",'M');
        map.put("course","数据库");
        multiTableDao.getScoreByStudent(map).forEach(System.out::println);
    }

    @Test
    public void testGetAverageScore() {
        //3.统计出各门课的平均成绩并从高到低排序输出
        List<Map> courseScore = multiTableDao.getAverageScore();
        for (Map map : courseScore) {
            System.out.println(map.get("cname") + ":" + map.get("avgscore"));
        }
    }
}
