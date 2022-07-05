package com.hexiang.springmvc.service.impl;

import com.hexiang.springmvc.mapper.StarMapper;
import com.hexiang.springmvc.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 何翔
 * @date: 2022/5/9
 * @description:
 */

@Service
public class StarServiceImpl implements StarService {

     @Autowired
     private StarMapper starMapper;

      @Override
      public Integer getVote(Integer id) {
         starMapper.updateVote(id);
       return starMapper.getVote(id);
      }
}
