package com.vow.demo.domain.vo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author: wushaopeng
 * @date: 2023/1/31 14:13
 */
@Data
@Document(indexName = "shakespeare")
public class Shakespeare {

    @Id
    @Field(name = "line_id")
    private Long lineId;

    @Field(name = "line_number")
    private String lineNumber;

    @Field(name = "play_name")
    private String playName;

    @Field(name = "speaker")
    private String speaker;

    @Field(name = "speech_number")
    private Long speechNumber;

    @Field(name = "text_entry")
    private String textEntry;
}
