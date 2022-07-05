package com.hexiang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: hexiang
 * @date: 2022/2/28
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    private Integer stuId;
    private String stuNum;
    private String stuName;
    private String stuGender;
    private Integer stuAge;
}
