package com.galen.esdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Galen
 * @version 1.0
 * @date 2019/7/30 17:48
 */
@Document(indexName = "galen",type = "Book")
@Getter
@Setter
public class Book implements Serializable {
    @Id
    private Integer id;
    private String bookname;
    private String author;
    private Integer word_count;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date pub_date;
}
