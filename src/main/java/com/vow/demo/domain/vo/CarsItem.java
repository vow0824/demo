package com.vow.demo.domain.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author: wushaopeng
 * @date: 2023/1/31 13:57
 */
@Data
@Document(indexName = "cars")
public class CarsItem {

    @Id
    private Long id;

    //@Field
    private String color;

    private String make;

    private Long price;

    private String sold;
}
