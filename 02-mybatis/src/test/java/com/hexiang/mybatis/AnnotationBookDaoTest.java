package com.hexiang.mybatis;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hexiang.mybatis.dao.AnnotationBookDao;
import com.hexiang.mybatis.pojo.Book;
import com.hexiang.mybatis.utils.MybatisUtil;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * @author: hexiang
 * @date: 2022/2/28
 * @description: BookDao测试
 */

public class AnnotationBookDaoTest {

    Integer testId = 14;
    AnnotationBookDao bookDao = MybatisUtil.getMapper(AnnotationBookDao.class,true);

    @Test
    public void testInsertBook()  {
        Book book =new Book(null, "Mybatis", "阿帕奇", new Date(), 99);
        Integer result = bookDao.insert(book);
        assertEquals(1,(int)result);
        //主键回传检验
        System.out.println(book.getId());
    }

    @Test
    public void testDeleteBookById() {
        Integer result = bookDao.delete(testId);
        assertEquals(1,(int)result);
    }

    @Test
    public void testUpdateBook(){
        Book book = bookDao.getOneBook(testId);
        if (book==null) return;
        book.setTitle("新"+book.getTitle());
        book.setPrice(66);
        assertEquals(1,(int)bookDao.update(book));
    }

    @Test
    public void testGetAllBook(){
        bookDao.getAllBook().forEach(System.out::println);
    }

    @Test
    public void testGetOneBook(){
        System.out.println(bookDao.getOneBook(testId));
    }

    @Test
    public void testGetBookCount(){
        System.out.println(bookDao.getBookCount());
    }

    @Test
    public void testGetBookByPage(){
        bookDao.getBookByPage(0,2).forEach(System.out::println);
    }

    @Test
    public void testGetBookByPageHelper(){
        PageHelper.startPage(1,2);
        List<Book> books = bookDao.getAllBook();
        PageInfo<Book> pageInfo = new PageInfo<>(books);
        pageInfo.getList().forEach(System.out::println);
    }


}
