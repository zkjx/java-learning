package com.hexiang.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: hexiang
 * @date: 2022/3/28
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class StudentCourseScore {
    private String StudentName;
    private char StudentSex;
    private String courseName;
    private Integer courseScore;
}
