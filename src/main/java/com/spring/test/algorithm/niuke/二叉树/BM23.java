package com.spring.test.algorithm.niuke.二叉树;

import com.spring.test.algorithm.niuke.TreeNode;
import lombok.val;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的前序遍历
 * @author hlshi3
 * @date 2022/4/21 8:45
 */
public class BM23 {

    public int[] preorderTraversal (TreeNode root) {
        // write code here
        List<Integer> list = new ArrayList<>();
        dfs(list, root);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i]=list.get(i);
        }
        return res;
    }

    private void dfs(List<Integer> list, TreeNode root){
        list.add(root.val);

        dfs(list, root.left);
        dfs(list, root.right);

    }


}
