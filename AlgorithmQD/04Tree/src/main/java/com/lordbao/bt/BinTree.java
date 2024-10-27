package com.lordbao.bt;


import java.util.LinkedList;

/**
 * @Author Lord_Bao
 * @Date 2024/10/27 20:01
 * @Version 1.0
 *
 * 二叉树
 */
public class BinTree<T> {
    private TreeNode root;

    private class TreeNode{
        private T data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(TreeNode left, TreeNode right,T data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        private  TreeNode(T data){
            this.data= data;
        }
    }


    public BinTree(T [] data){
        createTreeByLayer(data);
    }

    //层次建树
    private void createTreeByLayer(T [] data){
        if(data==null || data.length==0){
           return;
        }

        root=new TreeNode(data[0]);
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int i=1;
        while (!queue.isEmpty()){
            TreeNode treeNode = queue.removeFirst();
            //treeNode
            if(i<data.length){
                if(data[i]!=null){
                    treeNode.left=new TreeNode(data[i]);
                    queue.addLast(treeNode.left);
                }
            }else {
                break;
            }
            i++;
            if(i<data.length){
                if(data[i]!=null){
                    treeNode.right=new TreeNode(data[i]);
                    queue.addLast(treeNode.right);
                }
            }else {
                break;
            }
            i++;
        }
    }

    //先序遍历
    public void preOrderTraverse(){
        System.out.print("先序遍历: ");
        preOrderTraverse(root);
        System.out.println();
    }
    private void preOrderTraverse(TreeNode treeNode){
        if(treeNode==null){
            return;
        }
        System.out.print(treeNode.data+" ");
        preOrderTraverse(treeNode.left);
        preOrderTraverse(treeNode.right);
    }

    //中序遍历
    public void inOrderTraverse(){
        System.out.print("中序遍历: ");
        inOrderTraverse(root);
        System.out.println();
    }

    private void inOrderTraverse(TreeNode treeNode){
        if(treeNode==null){
            return;
        }
        inOrderTraverse(treeNode.left);
        System.out.print(treeNode.data+" ");
        inOrderTraverse(treeNode.right);
    }

    //后序遍历
    public void postOrderTraverse(){
        System.out.print("后序遍历: ");
        postOrderTraverse(root);
        System.out.println();
    }

    private void postOrderTraverse(TreeNode treeNode){
        if(treeNode==null){
            return;
        }
        postOrderTraverse(treeNode.left);
        postOrderTraverse(treeNode.right);
        System.out.print(treeNode.data+" ");
    }


    public void layerTraverse(){
        System.out.print("层次遍历:");
        if(root==null){
            return;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.addLast(root);
        while (!list.isEmpty()){
            TreeNode first = list.removeFirst();
            System.out.print(first.data+" ");

            if(first.left!=null){
                list.addLast(first.left);
            }
            if(first.right!=null){
                list.addLast(first.right);
            }
        }
        System.out.println();
    }

    //返回以t为根节点的子树高度
    private int height(TreeNode t){
        if(t==null){
            return 0;
        }
        return Math.max(height(t.left),height(t.right))+1;
    }
    public int height(){
        return height(root);
    }



    private int size(TreeNode root) {
        if(root==null){
            return 0;
        }
        return size(root.left)+size(root.right)+1;
    }
    public int size(){
        return size(root);
    }


    //返回以root为根节点的子树的叶子个数
    private int leaves(TreeNode root){
        if(root==null){
            return 0;
        }
        if(root.left==null && root.right==null){
            return 1;
        }
        return leaves(root.left) + leaves(root.right);
    }
    public int leaves(){
        return leaves(root);
    }
}
