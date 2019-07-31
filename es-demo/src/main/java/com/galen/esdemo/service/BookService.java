package com.galen.esdemo.service;

import com.galen.esdemo.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Galen
 * @version 1.0
 * @date 2019/7/31 10:54
 */
@Service
public interface BookService {
    /**
     * 新增信息
     * @param book
     * @return
     */
    boolean insert(Book book);

    /**
     * 通过输入的值去查询数据
     * @param searchContent 查询的值
     * @return
     */
    List<Book> search(String searchContent);

    /**
     * 通过输入的值去查询数据 分页查询
     * @param pageNumber
     * @param pageSize
     * @param searchContent 查询的值
     * @return
     */
    List<Book> searchBook(Integer pageNumber, Integer pageSize, String searchContent) ;

    /**
     * 通过输入的值去查询数据 根据权重去搜索
     * @param searchContent 查询的值
     * @return
     */
    List<Book> searchBookByWeight(String searchContent) ;

    /**
     * 通过输入的值去删除索引值
     * @param book book主要是根据id去删除
     */
    void deleteBook(Book book) ;

    /**
     * 分页查询
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<Book> searchBookByPage(Integer pageNumber, Integer pageSize);
}
