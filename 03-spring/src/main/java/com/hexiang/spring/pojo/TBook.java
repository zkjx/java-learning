package com.hexiang.spring.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *
 * @TableName t_book
 */
@TableName(value ="t_book")
@Data
public class TBook {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField(value = "status")
    private String status;

    /**
     *
     */
    @TableField(value = "num")
    private Integer num;


}
