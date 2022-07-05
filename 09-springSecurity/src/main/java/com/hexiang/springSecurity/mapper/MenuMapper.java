package com.hexiang.springSecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hexiang.springSecurity.pojo.Menu;

import java.util.List;

/**
 * @author: hexiang
 * @date: 2022/3/25
 * @description:
 */

public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(Long id);
}
