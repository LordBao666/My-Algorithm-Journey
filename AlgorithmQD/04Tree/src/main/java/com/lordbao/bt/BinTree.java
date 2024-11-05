package com.lordbao.bt;


import java.util.LinkedList;

/**
 * @Author Lord_Bao
 * @Date 2024/10/27 20:01
 * @Version 1.0
 * <p>
 * 二叉树
 */
public class BinTree<T> {
    private TreeNode root;

    private class TreeNode {
        private T data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(TreeNode left, TreeNode right, T data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        private TreeNode(T data) {
            this.data = data;
        }
    }


    public BinTree(T[] data) {
        createTreeByLayer(data);
    }

    //层次建树
    private void createTreeByLayer(T[] data) {
        if (data == null || data.length == 0) {
            return;
        }

        root = new TreeNode(data[0]);
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.removeFirst();
            //treeNode
            if (i < data.length) {
                if (data[i] != null) {
                    treeNode.left = new TreeNode(data[i]);
                    queue.addLast(treeNode.left);
                }
            } else {
                break;
            }
            i++;
            if (i < data.length) {
                if (data[i] != null) {
                    treeNode.right = new TreeNode(data[i]);
                    queue.addLast(treeNode.right);
                }
            } else {
                break;
            }
            i++;
        }
    }

    //先序遍历
    public void preOrderTraverse() {
        System.out.print("先序遍历: ");
//        preOrderTraverse(root);
        preOrderTraverseIter(root);
        System.out.println();
    }

    private void preOrderTraverse(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.print(treeNode.data + " ");
        preOrderTraverse(treeNode.left);
        preOrderTraverse(treeNode.right);
    }

    //先序遍历迭代版
    private void preOrderTraverseIter(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(treeNode);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.data + " ");
            //顺序不要乱，如果左右孩子不为空，一定要先push右孩子，保证右孩子在栈底
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    //中序遍历
    public void inOrderTraverse() {
        System.out.print("中序遍历: ");
//        inOrderTraverse(root);
        inOrderTraverseIter(root);
        System.out.println();
    }

    private void inOrderTraverse(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inOrderTraverse(treeNode.left);
        System.out.print(treeNode.data + " ");
        inOrderTraverse(treeNode.right);
    }

    /**
     * 中序遍历迭代版
     * 中序遍历是左根右，所以一开始就是一直向左查找，直到找到最左端的节点，在探索
     * 的过程中，需要用栈将沿途的节点存储起来。找到最左端节点之后，打印它的值，接
     * 下来判断它的右孩子是否为空，若为空，则回退到上一个节点；若不为空，则需要针
     * 对右孩子一直找左孩子的操作，这部分和最开始描述的一样。
     * <p>
     * 程序退出的时机是栈空。
     */
    private void inOrderTraverseIter(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stackNodeInLeftPath(treeNode, stack);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.data + " ");
            //下面的判空条件可加可不加，因为底层stackNodeInLeftPath已经做了null的处理
            if (node.right != null) {
                stackNodeInLeftPath(node.right, stack);
            }
        }
    }

    /**
     * 一直往左探索，存储路径上的节点
     */
    private void stackNodeInLeftPath(TreeNode treeNode, LinkedList<TreeNode> stack) {
        while (treeNode != null) {
            stack.push(treeNode);
            treeNode = treeNode.left;
        }
    }

    //后序遍历
    public void postOrderTraverse() {
        System.out.print("后序遍历: ");
//        postOrderTraverse(root);
        postOrderTraverseIter(root);
        System.out.println();
    }

    private void postOrderTraverse(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        postOrderTraverse(treeNode.left);
        postOrderTraverse(treeNode.right);
        System.out.print(treeNode.data + " ");
    }

    /***
     *
     * 后序遍历迭代版本
     *   后序遍历是左右根，因此是一直往左找，实在不行再往右找，直到找到一个叶子节点
     *   打印该叶子节点的值，用栈存储沿途的节点。找到叶子节点之后，回退到上一个节点，
     *   如果该叶子节点是父节点(回退节点)的左孩子，那么父节点还需要继续探索右孩子，
     *   如果该叶子节点是父节点的右孩子，打印父节点的值，继续回退节点，注意此时父节点
     *   相当于是刚回退节点的孩子，处理思路和上面一样的。
     *
     *   程序退出时机为 栈为空。显然后序遍历要稍微麻烦一点，要用到两个指针。
     */
    private void postOrderTraverseIter(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode p = stackNodeInPostOrder(treeNode, stack);
        while (!stack.isEmpty()) {
            System.out.print(p.data + " ");
            //此时不能直接弹栈，因为p 可能是parent的左孩子，因此还有探索右孩子的必要
            TreeNode parent = stack.peek();
            if (parent.right != p && parent.right != null) {
                p = stackNodeInPostOrder(parent.right, stack);
            } else {
                p = parent;
                stack.pop();
            }
        }
        System.out.print(p.data + " ");
    }

    /**
     * 一直往左找，实在不行再往右找，直到找到叶子节点为止，存储沿途的节点，并返回叶子节点。
     * 假定参数treeNode 非空
     */
    private TreeNode stackNodeInPostOrder(TreeNode treeNode, LinkedList<TreeNode> stack) {

        while (true) {
            if (treeNode.left != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else if (treeNode.right != null) {
                stack.push(treeNode);
                treeNode = treeNode.right;
            } else {
                return treeNode;
            }
        }
    }

    public void layerTraverse() {
        System.out.print("层次遍历:");
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.addLast(root);
        while (!list.isEmpty()) {
            TreeNode first = list.removeFirst();
            System.out.print(first.data + " ");

            if (first.left != null) {
                list.addLast(first.left);
            }
            if (first.right != null) {
                list.addLast(first.right);
            }
        }
        System.out.println();
    }

    //返回以t为根节点的子树高度
    private int height(TreeNode t) {
        if (t == null) {
            return 0;
        }
        return Math.max(height(t.left), height(t.right)) + 1;
    }

    public int height() {
        return height(root);
    }


    private int size(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return size(root.left) + size(root.right) + 1;
    }

    public int size() {
        return size(root);
    }


    //返回以root为根节点的子树的叶子个数
    private int leaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return leaves(root.left) + leaves(root.right);
    }

    public int leaves() {
        return leaves(root);
    }
}
