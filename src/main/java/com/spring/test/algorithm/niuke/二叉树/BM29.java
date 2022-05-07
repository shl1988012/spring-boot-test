package com.spring.test.algorithm.niuke.二叉树;

import com.spring.test.algorithm.niuke.TreeNode;

/**
 * 二叉树中和为某一值的路径(一)
 *
 *  给定一个二叉树root和一个值 sum ，判断是否有从根节点到叶子节点的节点值之和等于 sum 的路径。
 * 1.该题路径定义为从树的根结点开始往下一直到叶子结点所经过的结点
 * 2.叶子节点是指没有子节点的节点
 * 3.路径只能从父节点到子节点，不能从子节点到父节点
 * 4.总节点数目为n
 *
 * @author hlshi3
 * @date 2022/4/23 8:28
 */
public class BM29 {


    public boolean hasPathSum (TreeNode root, int sum) {
        // write code here
        if(root == null){
            return false;
        }
        if(root.left == null && root.right == null && sum==root.val){
            return true;
        }
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }

}
