package com.spring.test.algorithm.TreeDemo;

/**
 * @author hlshi3
 * @date 2020/7/27 14:16
 */
public class MyString implements Comparable<MyString>{

    private String string;

    public MyString(String s){
        this.string = s;
    }
    public String getString(){
        return this.string;
    }
    public MyString getParent(){
        String parent = this.string.substring(0, string.length()-2);
        return new MyString(parent);
    }
    //因为我们要根据自己的数据，找到自己的合适父节点。
    //由题目的意思，自己的数据抹去后两位，就是自己的父节点的数据，所以根据数据返回父节点
    @Override
    public int compareTo(MyString o) {
        if(this.string.length() > o.string.length())
            return 1;
        else if(this.string.length() < o.string.length())
            return -1;
        else {
            if(this.string.compareTo(o.string) > 0)
                return 1;
            else if(this.string.compareTo(o.string) < 0)
                return -1;
            else return 0;
        }
    }

    @Override
    public String toString() {
        return string;
    }
}
