package com.spring.test.algorithm;

import java.util.LinkedList;
import java.util.Stack;

public class BinaryTree {


    TreeNode root;

    public BinaryTree(int[] array){
        root=makeBinaryTreeByArray(array,1);
    }


    //深度优先遍历：Depth First Search
    public void deepOrder(){
        if(root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode treeNode = stack.pop(); //出栈
            System.out.println("node value:"+ treeNode.value);
            if(treeNode.right != null){


            }


        }


    }



    //创建一棵树
    /**
     * 采用递归的方式创建一颗二叉树
     * 传入的是二叉树的数组表示法
     * 构造后是二叉树的二叉链表表示法
     */
    public static TreeNode makeBinaryTreeByArray(int[] array,int index){
        if(index<array.length){
            int value=array[index];
            if(value!=0){
                TreeNode t=new TreeNode(value);
                array[index]=0;
                t.left=makeBinaryTreeByArray(array,index*2);
                t.right=makeBinaryTreeByArray(array,index*2+1);
                return t;
            }
        }
        return null;
    }

}
