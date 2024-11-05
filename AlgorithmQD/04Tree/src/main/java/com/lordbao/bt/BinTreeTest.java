package com.lordbao.bt;


/**
 * @Author Lord_Bao
 * @Date 2024/10/27 22:07
 * @Version 1.0
 */
public class BinTreeTest {

    public static void main(String[] args) {
        Character [] data = {'a','b','f','c','d','g',null,null,null,'e',null,null,'h',null,null,null,null};
        BinTree<Character> tree = new BinTree<>(data);
//        tree.preOrderTraverse();
//        tree.inOrderTraverse();
        tree.postOrderTraverse();
//        tree.layerTraverse();
        System.out.println(tree.height());
        System.out.println(tree.size());
        System.out.println(tree.leaves());
    }
}
