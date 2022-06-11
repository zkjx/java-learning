package com.hexiang.mybatisplus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: hexiang
 * @date: 2022/3/23
 * @description: user实体类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private Long id;
    private String userName;
    private String password;
    private String name;
    private Integer age;
    private String address;
}
