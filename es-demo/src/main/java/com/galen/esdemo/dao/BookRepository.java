package com.galen.esdemo.dao;

import com.galen.esdemo.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Galen
 * @version 1.0
 * @date 2019/7/31 10:37
 */
@Component
public interface BookRepository extends ElasticsearchRepository<Book,Integer> {
    List<Book> findByBooknameLike(String bookname);

    List<Book> findAllByBookname(String bookname);
}
