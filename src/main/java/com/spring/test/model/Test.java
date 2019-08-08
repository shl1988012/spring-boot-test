package com.spring.test.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class Test {


    public static void main(String[] args) {


        ObjectMapper mapper = new ObjectMapper();

//        AccessTokenError accessTokenError = new AccessTokenError(100,"errorMessage");

//        String str ="{\n" +
//                "\"error_description\": \"Unable to get client from client database.\",\n" +
//                "\"error\": \"\"\n" +
//                "}";

        String str ="";
//        try {
//            mapper.readValue(str,AccessTokenResponse.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



        System.out.println(StringUtils.isNumeric("-1"));

    }
}
