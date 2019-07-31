package com.galen.esdemo.controller;

import com.galen.esdemo.dao.BookRepository;
import com.galen.esdemo.entity.Book;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Galen
 * @version 1.0
 * @date 2019/7/31 9:19
 */
@RestController
public class TestController {
    @Autowired
    TransportClient client;
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/get/book")
    public ResponseEntity getNovel(@RequestParam(name = "id", defaultValue = "")String id){
        if(id.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        GetResponse result = this.client.prepareGet("galen", "book", id).get();

        if (!result.isExists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(result.getSource(), HttpStatus.OK);
    }

    @GetMapping("/get/book/name")
    public ResponseEntity getBook(@RequestParam(name = "bookName", defaultValue = "")String bookName){
        if(bookName.isEmpty()){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        List<Book> list = bookRepository.findAllByBookname(bookName);

        System.out.println(list);

        if (list == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/get/book/list")
    public ResponseEntity getBookList(){
        Iterable<Book> books = bookRepository.findAll();
        Iterator<Book> iterator = books.iterator();
        List<Book> list = new ArrayList<>();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }

        System.out.println(list);

        if (list == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
