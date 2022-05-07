package com.spring.test.algorithm.niuke.二叉树;

import com.spring.test.algorithm.niuke.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵二叉树，判断其是否是自身的镜像（即：是否对称）
 * @author hlshi3
 * @date 2022/5/1 10:46
 */
public class BM31 {

    /**
     * 方法一：对树进行两次遍历 一次根左右，一次根右左，对比两次遍历结果
     * @param pRoot
     * @return
     */
    boolean isSymmetrical1(TreeNode pRoot) {
        //根左右打印： 135673576
        //根右左打印： 135673576
        List<Integer> list1 = new ArrayList();
        List<Integer> list2 = new ArrayList();
        dfs1(list1, pRoot);
        dfs2(list2, pRoot);

        for(int i =0; i< list1.size(); i++){
            if(list2.get(i)!= list1.get(i)){
               return false;
            }
        }
        return true;
    }

    private void dfs1(List<Integer> list1, TreeNode pRoot){
        if(pRoot ==null){
            return;
        }
        list1.add(pRoot.val);
        dfs1(list1,pRoot.left);
        dfs1(list1,pRoot.right);
    }

    private void dfs2(List<Integer> list2, TreeNode pRoot){
        if(pRoot ==null){
            return;
        }
        list2.add(pRoot.val);
        dfs1(list2,pRoot.right);
        dfs1(list2,pRoot.left);
    }

    /**
     * 方法二，递归比较节点
     * @param pRoot
     * @return
     */
    boolean isSymmetrical2(TreeNode pRoot){
        return compare(pRoot,pRoot);

    }

    public boolean compare(TreeNode root1, TreeNode root2){
        if(root1==null && root2 ==null){
            return true;
        }
        if(root1==null || root2 ==null){
            return false;
        }
        if(root1.val != root2.val){
            return false;
        }
        return compare(root1.left, root2.right) &&  compare(root1.right, root2.left);
    }






}
