package com.spring.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.test.model.FilePathRequest;
import com.spring.test.model.Item;
import com.spring.test.model.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AnalyseFileController {

//    private static final Logger log = LoggerFactory.getLogger(TestLogController.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    Map<String, Integer> errorType = new HashMap<>();

    @RequestMapping(path = "/analyseFile", produces = {"application/json"}, method = {RequestMethod.POST})
    public void testLog(@RequestBody FilePathRequest filePathRequest) throws IOException {
        List<String> filePaths = filePathRequest.getFilePaths();
        FileWriter fw = new FileWriter(filePathRequest.getOutputPath());
        for (String filePath : filePaths) {
            String fileContent = getFileContent(filePath);
            Items items = objectMapper.readValue(fileContent, Items.class);
            List<Item> itemList = items.getItems();
            for (int i = itemList.size() - 1; i > 0; i--) {
                String text = itemList.get(i).getText();
                if (text.contains("Alarm")) {
                    fw.write(text + "\r\n");
                }
                if (text.contains("Stack Trace")) {
                    int index = text.indexOf(" at ");
                    if (index < 0) {
                        fw.write(text + "\r\n");
                        if (errorType.containsKey(text)) {
                            errorType.put(text, errorType.get(text) + 1);
                        } else {
                            errorType.put(text, 1);
                        }
                    } else {
                        text = text.substring(0, index);
                        String[] splitErrorMessage = text.split(": ");
                        if (splitErrorMessage.length > 1) {
                            if (errorType.containsKey(splitErrorMessage[1])) {
                                errorType.put(splitErrorMessage[1], errorType.get(splitErrorMessage[1]) + 1);
                            } else {
                                errorType.put(splitErrorMessage[1], 1);
                            }
                        }
                        fw.write(text + "\r\n");
                    }
                }


//                if(text.contains("Stack Trace") || text.contains("Alarm") || text.contains("exception")){
//                    int index = text.indexOf(" at ");
//                    if(index<0){
//                        fw.write(text +"\r\n");
//                    }else{
//                        text = text.substring(0, index);
//                        String[]splitErrorMessage = text.split(": ");
//                        if(splitErrorMessage.length > 1){
//                            if(errorType.containsKey(splitErrorMessage[1])){
//                                errorType.put(splitErrorMessage[1], errorType.get(splitErrorMessage[1])+1);
//                            }else{
//                                errorType.put(splitErrorMessage[1], 1);
//                            }
//                        }
//                        fw.write(text +"\r\n");
//                    }
//                }
            }
        }

        //output map
        errorType.forEach((key, value) -> {
            System.out.println(key + ":" + value);
        });
        fw.close();
    }

    private String getFileContent(String filePath) {

        String encoding = "UTF-8";
        File file = new File(filePath);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
        }
        return null;
    }
}
