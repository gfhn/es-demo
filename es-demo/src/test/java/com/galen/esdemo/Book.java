package com.galen.esdemo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Galen
 * @version 1.0
 * @date 2019/7/30 17:48
 */
@Document(indexName = "galen",type = "Book")
@Data
public class Book implements Serializable {
    @Id
    private Integer id;
    private String bookname;
    private String author;
    private Integer wordCount;
    private Date pubDate;
}
