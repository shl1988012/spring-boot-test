package com.spring.test.algorithm.niuke;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GetWordsUtils {


    public static final String IS_END = "isEnd";


    private GetWordsUtils(){}


    /**
     * 创建匹配树
     * @param dicList 字典列表
     * @return
     */
    public static JSONObject createKeyWordTree(List<String> dicList){
        if(CollectionUtils.isEmpty(dicList)) {
            return null;
        }
        JSONObject keyWordTree = new JSONObject(dicList.size());
        addKeyInTree(dicList,keyWordTree);
        return keyWordTree;
    }


    /**
     * 添加词到匹配树
     * @param list 词
     * @param keyWordTree 树
     * @return
     */
    public static JSONObject addKeyInTree(List<String> list, JSONObject keyWordTree){
        for (String word :list) {
            JSONObject nowTree = keyWordTree;
            for(int i = 0 ; i < word.length() ; i++){
                char keyChar = word.charAt(i);       //转换成char型
                JSONObject wordTree = nowTree.getJSONObject(String.valueOf(keyChar));       //获取
                if(wordTree != null){        //如果存在该key，直接赋值
                    nowTree = wordTree;
                }else{//不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    JSONObject newWorMap = new JSONObject();
                    newWorMap.put(IS_END, "0");     //不是最后一个
                    nowTree.put(String.valueOf(keyChar), newWorMap);
                    nowTree = newWorMap;
                }
                if(i == word.length() - 1){
                    nowTree.put(IS_END, "1");    //最后一个
                }
            }
        }
        return keyWordTree;
    }


    /**
     * 获取匹配到到文字
     * @param txt 输入到文本
     * @param dic 匹配树
     * @return
     */
    public static Set<String> getElements(String txt, JSONObject dic){
        Set<String> elementSet = new HashSet<>();
        if(null == dic) {
            return elementSet;
        }
        for (int i = 0; i < txt.length(); i++) {
            //判断从本段第i个字段开始  是否有词汇，有则返回要长度
            List<Integer> lengthList = checkElements(txt, i, dic);
            for (Integer length : lengthList) {
                String word = txt.substring(i, i + length);
                elementSet.add(word);
            }
        }
        return elementSet;
    }


    /**
     * 返回词语的长度
     * @param txt 文本 词典
     * @param beginIndex 开始
     * @param dic 匹配树
     * @return 位置信息
     */
    private static List<Integer> checkElements(String txt, int beginIndex, JSONObject dic) {
        List<Integer> lengthList = new ArrayList<>();


        int matchFlag = 0;     //匹配标识数默认为0
        JSONObject nowTree = dic;
        int length = 0;
        for (int i = beginIndex; i < txt.length(); i++) {
            char word = txt.charAt(i);
            nowTree = nowTree.getJSONObject(String.valueOf(word));     //获取指定key
            if (nowTree != null) {     //存在，则判断是否为最后一个
                length++;     //找到相应key，匹配标识+1
                if ("1".equals(nowTree.getString(IS_END))) {       //如果为最后一个匹配规则,结束循环，返回匹配标识数
                    matchFlag = length;//这个关键词的长度
                    lengthList.add(matchFlag);//此次匹配的要素词长度添加到列表中
                }
            } else {     //不存在，直接返回
                break;
            }
        }
        return lengthList;
    }


    public static void main(String[] args) throws Exception {
        //BufferedReader是可以按行读取文件
        FileInputStream inputStream = new FileInputStream("ciku.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = null;
        List<String> wordList = new ArrayList<>(700000);
        while((str = bufferedReader.readLine()) != null)
        {
            wordList.add(str);
        }
        //close
        inputStream.close();
        bufferedReader.close();

        System.out.println(wordList.size());

        JSONObject dict =  createKeyWordTree(wordList);
        Set<String> sets = getElements("aaa",dict);
        System.out.println(sets);

    }
}
