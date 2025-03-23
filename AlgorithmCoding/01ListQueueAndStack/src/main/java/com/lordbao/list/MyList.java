package com.lordbao.list;


import java.util.Iterator;

/**
 * @Author Lord_Bao
 * @Date 2025/3/23 15:22
 * @Version 1.0
 */
public interface MyList<E> extends Iterable<E> {


    /**
     * 清空列表
     */
    void clearList();

    /**
     * 在位置i处插入元素e
     */
    void insertList(int i, E e);

    /**
     * 删除位置i处的元素，并将其返回
     */
    E deleteList(int i);

    /***
     * 返回元素个数
     */
    int size();

    /***
     *
     * 返回列表是否为空
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 查找元素e在MyList中的下标,若不存在则返回-1
     */
    int locateElem(E e);

    /**
     * 返回下标i处的元素
     */
    E getElem(int i);

    /***
     *
     * 添加首元素
     */
    void addFirst(E e);

    /***
     *
     * 删除首元素
     */
    E removeFirst();

    /***
     *
     * 添加尾元素
     */
    void addLast(E e);

    /***
     *
     * 删除尾元素
     */
    E removeLast();

    /**
     * 迭代器
     */
    default Iterator<E> iterator() {
        throw new UnsupportedOperationException("iterator is not implemented");
    }

    /**
     * 返回首元素
     */
    default E getFirst() {
        return getElem(0);
    }

    /**
     * 返回尾元素
     */
    default E getLast() {
        return getElem(size() - 1);
    }
}