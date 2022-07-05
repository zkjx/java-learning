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
public class Score {
    private Integer sid;
    private Integer cid;
    private Integer score;
}
