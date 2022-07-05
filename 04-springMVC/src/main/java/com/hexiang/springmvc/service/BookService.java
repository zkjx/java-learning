package com.hexiang.springmvc.service;

import java.util.List;

/**
 * @author: 何翔
 * @date: 2022/5/10
 * @description:
 */

public interface BookService {
    void sale(Integer num);
    List<Integer> getBookNum();
}
