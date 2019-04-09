package com.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 给定一个二叉树，返回它的中序遍历。
 * 输入: [1,null,2,3]
 * 1
 *  \
 *   2
 *  /
 * 3
 * 输出: [1,3,2]
 * @Author CLD
 * @Date 2019/4/9 21:37
 */
public class inorderTraversal {
    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public static List<Integer> main(String[] args){
        TreeNode root=new TreeNode(0);
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
    }

}
