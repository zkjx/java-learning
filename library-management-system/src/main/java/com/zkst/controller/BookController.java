package com.zkst.controller;

;
import com.zkst.domain.Book;
import com.zkst.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Book")
public class BookController {

    @Autowired
    private BookService bookService;

    // 该添加是添加到tb_library的数据库中的
    @PostMapping("/add_tb_library")
    public Result save(@RequestBody Book book) {
        Boolean flag = bookService.save(book);
        return new Result(flag?Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    // 该添加是添加到borrowing_records的数据库中的
    @PostMapping("/add_borrowing_records")
    public Result add(@RequestBody Book book) {
        Boolean flag = bookService.save(book);
        return new Result(flag?Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    @PutMapping
    public Result update(@RequestBody Book book) {
        Boolean flag = bookService.update(book);
        return new Result(flag?Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Boolean flag = bookService.delete(id);
        return new Result(flag?Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

   @GetMapping
    public Result getAll() {
       List<Book> books = bookService.getAll();
       Integer code=books!=null?Code.GET_OK:Code.GET_ERR;
       String msg=books!=null?"":"数据查询失败";
       return new Result(code,books,msg);
   }

    @GetMapping("/getid/{id}")
    public Result getById(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        Integer code=book!=null?Code.GET_OK:Code.GET_ERR;
        String msg=book!=null?"":"数据查询失败";
        return new Result(code,book,msg);
    }

    @GetMapping( ("/getBookName/{bookName}"))
    public Result getByBookName(@PathVariable String bookName)
    {

        List<Book> byBookName = bookService.getByBookName(bookName);
        Integer code=byBookName!=null?Code.GET_OK:Code.GET_ERR;
        String msg=byBookName!=null?"":"书库中没有该书名的书";
        return new Result(code,byBookName,msg);

    }

    @GetMapping(value = ("/getBookNumber/{bookNumber}"))
     public  Result getByBookNumber(@PathVariable String bookNumber)
     {
         Book book=bookService.getByBookNumber(bookNumber);
         Integer code = book != null ? Code.GET_OK : Code.GET_ERR;
         String msg = book != null ? "" : "书库中没有该书,请重新核实书号";
         return new Result(code,book,msg);

     }
}
