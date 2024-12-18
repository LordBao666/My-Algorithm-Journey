package com.lordbao.tree.binTree;


import java.util.LinkedList;

/**
 * @Author Lord_Bao
 * @Date 2024/12/13 14:54
 * @Version 1.0
 */
public class BinTree {

    private TreeNode root;

    public static class TreeNode {
        private TreeNode left;
        private TreeNode right;
        private int data;
    }




    /*--------------------------先序遍历非递归版本(开始)-------------------------------- */

    /**
     * 先序遍历非递归版本
     */
    public static void preOrderIter(TreeNode node) {
        if (node == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode t = stack.pop();
            //访问节点
            System.out.println(t.data);
            if (t.right != null) {
                stack.push(t.right);
            }
            if (t.left != null) {
                stack.push(t.left);
            }
        }
    }
    /*--------------------------先序遍历非递归版本(结束)-------------------------------- */



    /*--------------------------中序遍历非递归版本(开始)-------------------------------- */

    /**
     * 将节点的左侧分支依次入栈
     */
    public static void stackLeft(TreeNode node, LinkedList<TreeNode> stack) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    /**
     * 中序遍历非递归版本
     */
    public static void inOrderIter(TreeNode node) {
        if (node == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stackLeft(node, stack);
        while (!stack.isEmpty()) {
            TreeNode p = stack.pop();
            System.out.println(p.data);//访问当前节点
            stackLeft(p.right, stack);//处理右子树左侧分支
        }
    }
    /*--------------------------中序遍历非递归版本(结束)-------------------------------- */


    /*--------------------------后序遍历非递归版本(开始)-------------------------------- */
    /**
     * 后序遍历非递归版本
     */
    public static void postOrderIter(TreeNode node) {
        if (node == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(node);
        TreeNode pre = new TreeNode();    //表示上一个访问的节点
        while (!stack.isEmpty()) {
            TreeNode p = stack.peek();

            if(p.left!=null && (p.left!=pre && p.right!=pre)){//左孩子存在并且左孩子未被访问过
                stack.push(p.left);
            }else if(p.right!=null && p.right!=pre){//右孩子存在且右孩子未被访问过
                stack.push(p.right);
            }else {
                // 左右子树都已访问，访问当前节点
                stack.pop();
                System.out.println(p.data);
                pre=p;//更新pre
            }
        }
    }
    /*--------------------------后序遍历非递归版本(结束)-------------------------------- */


}
