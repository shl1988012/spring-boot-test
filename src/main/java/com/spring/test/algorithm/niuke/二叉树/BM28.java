package com.spring.test.algorithm.niuke.二叉树;

import com.spring.test.algorithm.niuke.TreeNode;

/**
 * 二叉树的最大深度
 *
 *  求给定二叉树的最大深度，
 * 深度是指树的根节点到任一叶子节点路径上节点的数量。
 * 最大深度是所有叶子节点的深度的最大值。
 *
 * @author hlshi3
 * @date 2022/4/21 12:27
 */
public class BM28 {

    public int maxDepth (TreeNode root) {
        // write code here
        if(root == null){
            return 0;
        }

        int leftLength = maxDepth(root.left);
        int rightLength = maxDepth(root.right);
        return Math.max(leftLength, rightLength)+1;

    }

}
