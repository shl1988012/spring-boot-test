package com.spring.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private String id;
    private String roomId;
    private String roomType;
    private String text;
    private String personId;
    private String personEmail;
    private String markdown;
    private String html;
    private String created;
}
