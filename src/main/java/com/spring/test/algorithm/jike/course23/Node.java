package com.spring.test.algorithm.jike.course23;

/**
 * 树的节点
 * @author hlshi3
 * @date 2021/1/26 14:47
 */
public class Node {

    public int value;

    public Node left;

    public Node right;

    public Node(){

    }

    public Node(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }


    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

}
