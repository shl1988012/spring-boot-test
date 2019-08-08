package com.spring.test.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.test.model.Item;

public class ObjectUtils {

    public static  ObjectMapper mapper = new ObjectMapper();

    //  object -> string
    public static String objectToString(Object object) {
        try {
            return mapper.writer().withDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

        Item item = new Item();
        item.setId("123");
        System.out.println(objectToString(item));
    }
}
