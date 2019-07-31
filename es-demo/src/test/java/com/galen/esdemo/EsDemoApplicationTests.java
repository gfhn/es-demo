package com.galen.esdemo;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsDemoApplicationTests {
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    TransportClient client;

    @Test
    public void contextLoads() {
    }

    @Test
    public void queryForList(){
        /*SearchQuery searchQuery = new NativeSearchQueryBuilder().build();
        List<Book> books = elasticsearchTemplate.queryForList(searchQuery,Book.class);
        for (Book book : books) {
            System.out.println(book);
        }*/
        GetResponse response = client.prepareGet("galen", "book", "1").get();
        System.out.println(response);
    }

    @Test
    public void queryForList2(){
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(new MatchQueryBuilder("id","3")).build();
        List<Book> books = elasticsearchTemplate.queryForList(searchQuery,Book.class);
        for (Book book : books) {
            System.out.println(book);
        }
    }
}
