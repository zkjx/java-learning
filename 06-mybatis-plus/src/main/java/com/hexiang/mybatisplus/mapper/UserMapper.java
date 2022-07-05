package com.hexiang.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.hexiang.mybatisplus.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author: hexiang
 * @date: 2022/3/23
 * @description:
 */

public interface UserMapper extends BaseMapper<User> {

    User findMyUser(Long id);

    User findMyUserByWrapper(@Param(Constants.WRAPPER)Wrapper<User> wrapper);
}
