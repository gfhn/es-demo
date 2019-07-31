package com.galen.esdemo.controller;

import com.galen.esdemo.entity.Book;
import com.galen.esdemo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Galen
 * @version 1.0
 * @date 2019/7/31 11:39
 */
@Slf4j
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/add")
    public String esSave(@RequestBody Book book) throws Exception {
        log.info("save"+book.toString());
        try{
            bookService.insert(book);
            return "success";
        }catch(Exception e){
            log.error("入库失败");
            return "fail";
        }
    }

    @RequestMapping("/query")
    public List<Book> esSearch(@RequestParam(value = "queryContent", required = false) String queryContent) throws Exception{
        log.info("query："+queryContent);
        List<Book> bookList= bookService.search(queryContent);
        for(Book book:bookList){
            System.out.println("query user:"+book.getBookname()+" "+book.getAuthor());
        }
        return bookList;
    }

    @RequestMapping(value = "/query/page",method = RequestMethod.GET)
    public List<Book> esSearchPage(@RequestParam(value = "pageNumber") Integer pageNumber,
                                   @RequestParam(value = "pageSize") Integer pageSize,
                                   @RequestParam(value = "queryContent") String queryContent) throws Exception{
        log.info("query："+queryContent);
        List<Book> bookList= bookService.searchBook(pageNumber,pageSize,queryContent);
        for(Book book:bookList){
            System.out.println("query user:"+book.getBookname()+" "+book.getAuthor());
        }
        return bookList;
    }

    @RequestMapping(value = "/query/searchBookByPage",method = RequestMethod.GET)
    public List<Book> searchBookByPage(@RequestParam(value = "pageNumber") Integer pageNumber,
                                   @RequestParam(value = "pageSize") Integer pageSize) throws Exception{
        List<Book> books = bookService.searchBookByPage(pageNumber, pageSize);
        for(Book book:books){
            System.out.println("query user:"+book.getBookname()+" "+book.getAuthor());
        }
        return books;
    }

    @RequestMapping("/update")
    public String esUpdate(@RequestBody Book book) throws Exception {
        log.info("update"+book.toString());
        try{
            bookService.insert(book);
            return "success";
        }catch(Exception e){
            log.error("更新失败");
            return "fail";
        }
    }

    @RequestMapping("/delete")
    public String esDelete(@RequestParam(value = "id", required = true) Integer id) throws Exception {
        Book book = new Book();
        book.setId(id);
        try{
            bookService.deleteBook(book);
            log.info("delete "+id);
            return "success";
        }catch(Exception e){
            log.error("删除失败");
            return "fail";
        }
    }
}
