package com.hexiang.spring.service.impl;

import com.hexiang.spring.dao.TBookDao;
import com.hexiang.spring.pojo.TBook;
import com.hexiang.spring.service.TBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author hexiang
* @description 针对表【t_book】的数据库操作Service实现
* @createDate 2022-04-25 14:24:48
*/
@Service("bookServiceImpl")
public class BookServiceImpl implements TBookService{

    @Autowired
    private TBookDao tBookDao;

    @Override
    public List<TBook> getBookList() {
        return tBookDao.getBookList();
    }

    @Override
    @Transactional
    public String sellBook(String storeName, String sellName, Integer sellNum) {
        TBook book = tBookDao.findBookByName(storeName);
        int currentNum = book.getNum()-sellNum;
        if(currentNum<0) return "库存不足，当前的库存数量为+"+book.getNum()+"本书！";
        book.setNum(currentNum);
        tBookDao.updateBook(book);
        int bug = 1/0;
        book = tBookDao.findBookByName(sellName);
        currentNum = book.getNum()+sellNum;
        book.setNum(currentNum);
        tBookDao.updateBook(book);
        return "销售成功！";
    }

}




