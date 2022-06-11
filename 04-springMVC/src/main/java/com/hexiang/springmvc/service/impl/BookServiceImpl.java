package com.hexiang.springmvc.service.impl;

import com.hexiang.springmvc.mapper.BookMapper;
import com.hexiang.springmvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 何翔
 * @date: 2022/5/10
 * @description:
 */

@Service
public class BookServiceImpl implements BookService {

 @Autowired
 private BookMapper bookMapper;

     @Override
     @Transactional
     public void sale(Integer num) {
         bookMapper.store(num);
         bookMapper.sale(num);
     }

    @Override
    public List<Integer> getBookNum() {
        return bookMapper.getBookNum();
    }
}
