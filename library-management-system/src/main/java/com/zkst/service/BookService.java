package com.zkst.service;


import com.zkst.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//使用事务
@Transactional
public interface BookService {



    public Boolean save(Book book);

    public Boolean update(Book book);

    public Boolean delete(Integer id);

    public List<Book> getAll();

    public Book getById(Integer id);

    public List<Book> getByBookName(String bookName);

    public Book getByBookNumber(String bookNumber);

    public boolean addBorrowingRecord(Book book);
}
