package com.galen.esdemo.service.impl;

import com.galen.esdemo.dao.BookRepository;
import com.galen.esdemo.entity.Book;
import com.galen.esdemo.service.BookService;
import org.apache.commons.collections.IteratorUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Galen
 * @version 1.0
 * @date 2019/7/31 10:58
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;
    
    @Override
    public boolean insert(Book book) {
        boolean falg=false;
        try{
            bookRepository.save(book);
            falg=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return falg;
    }

    @Override
    public List<Book> search(String searchContent) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        System.out.println("查询的语句:"+builder);
        Iterable<Book> searchResult = bookRepository.search(builder);
//        Iterator<Book> iterator = searchResult.iterator();
        List<Book> list=new ArrayList<Book>();

        searchResult.forEach(result -> list.add(result));

//        while (iterator.hasNext()) {
//            list.add(iterator.next());
//        }
        return list;
    }

    @Override
    public List<Book> searchBook(Integer pageNumber, Integer pageSize, String searchContent) {
        // 分页参数
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        System.out.println("查询的语句:" + searchQuery.getQuery().toString());
        Page<Book> searchPageResults = bookRepository.search(searchQuery);
        return searchPageResults.getContent();
    }

    @Override
    public List<Book> searchBookByWeight(String searchContent) {
        // 根据权重进行查询
        /*FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(10))
                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
                        ScoreFunctionBuilders.weightFactorFunction(100)).setMinScore(2);
        System.out.println("查询的语句:" + functionScoreQueryBuilder.toString());
        Iterable<Book> searchResult = bookRepository.search(functionScoreQueryBuilder);
        Iterator<Book> iterator = searchResult.iterator();
        List<Book> list=new ArrayList<Book>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;*/
        return null;
    }

    @Override
    public void deleteBook(Book book) {
        //根据id去删除对应的索引值
        bookRepository.delete(book);
    }


    @Override
    public List<Book> searchBookByPage(Integer pageNumber, Integer pageSize){
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        Page<Book> books = bookRepository.findAll(pageable);

        Iterator<Book> iterator = books.iterator();
        List<Book> list = IteratorUtils.toList(iterator);
//        List<Book> list = new ArrayList<>();
//        while (iterator.hasNext()){
//            list.add(iterator.next());
//        }

        return list;
    }
}
