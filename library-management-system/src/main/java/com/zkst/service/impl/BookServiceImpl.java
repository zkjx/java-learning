package com.zkst.service.impl;


import com.zkst.dao.BookDao;
import com.zkst.domain.Book;
import com.zkst.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;



    @Override
    public Boolean save(Book book) {
        return  bookDao.save(book)>0;

    }

    @Override
    public Boolean update(Book book) {
        bookDao.update(book);
        return bookDao.update(book)>0;
    }

    @Override
    public Boolean delete(Integer id) {

        return  bookDao.delete(id)>0;
    }

    @Override
    public List<Book> getAll() {
          return bookDao.getAll();
    }

    @Override
    public Book getById(Integer id) {
          return bookDao.getById(id);
    }

    @Override
    public List<Book> getByBookName(String bookName) {
        return  bookDao.getByBookName(bookName);
    }

    @Override
    public Book getByBookNumber(String bookNumber) {
        return bookDao.getByBookNumber(bookNumber);
    }

    @Override
    public boolean addBorrowingRecord(Book book) {
        return bookDao.addBorrowingRecord(book)>0;
    }
}
