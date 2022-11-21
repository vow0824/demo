package com.vow.demo.xml;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @Author: Administrator
 * @Date: 2022/11/16 10:12
 */
@Data
@ToString
public class MyDocument {

    private String path;

    private Integer startPosition;

    private Integer endPosition;

    private String name;

    private Map<String, String> attributes;

}
