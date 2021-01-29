package com.spring.test.algorithm.TreeDemo;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author hlshi3
 * @date 2020/7/27 11:30
 */
public class MuxTree<T> {

//    private T data;
    private MyString data;

    private  MuxTree parent;

    private List<MuxTree> children;


    public MuxTree(){
        this.data = null;
        this.parent = null;
        this.children = new LinkedList<>();
    }

    public MuxTree(String  data, MuxTree parent){
        this.data = new MyString(data);
        this.parent = parent;
        this.children = new LinkedList<>();
    }


    //向一个父节点添加一个子节点
    public  MuxTree insert(String data, MuxTree parent){
        MuxTree child = new MuxTree(data, parent);
        parent.getChildren().add(child);
        return child;
    }

    public MuxTree insert(MyString myString){
        String s = myString.getParent().getString();
        MuxTree parent = this.search(new MyString(s));
        if(parent != null)
            return this.insert(myString.getString(), parent);
        else return null;
    }

    //广度遍历
    public MuxTree search(MyString myString){
        Queue<MuxTree> queue = new LinkedList<>();
        MuxTree p = this;
        String s = p.data.getString();
        while(p != null){
            if(p.data.getString().equals(myString.getString())){
                return  p;
            }
            if(p.children != null){
               List<MuxTree> childrenList = p.children;
               for(MuxTree temp : childrenList){
                   queue.offer(temp);
                }
            }
            p = queue.poll();
        }
        return null;
    }

    public void preOrder(MuxTree temp){
        if(temp == null)
            return;
        MuxTree p = temp;
        int parentNum = 0;
        if(p.parent != null){
            while(p.parent != null){
                parentNum += p.data.getString().length();
                p = p.parent;
            }
            for(int i = 0; i < parentNum; i++){
                System.out.print(" ");
            }
            System.out.println(temp.data);
        }
        List<MuxTree> childrenList = temp.children;
        for(MuxTree index : childrenList){
            preOrder(index);
        }
        return ;
    }


    public MyString getData() {
        return data;
    }

    public void setData(MyString data) {
        this.data = data;
    }

    public MuxTree getParent() {
        return parent;
    }

    public void setParent(MuxTree parent) {
        this.parent = parent;
    }

    public List<MuxTree> getChildren() {
        return children;
    }

    public void setChildren(List<MuxTree> children) {
        this.children = children;
    }
}
