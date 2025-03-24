package com.lordbao.list;


import java.util.Iterator;

/**
 * @Author Lord_Bao
 * @Date 2025/3/24 15:16
 * @Version 1.0
 */
public interface MyDeque<E> extends Iterable<E> {
    /**
     * 清空Deque
     */
    void clear();


    /***
     * 返回元素个数
     */
    int size();

    /***
     *
     * 返回双端队列是否为空
     */
    default boolean isEmpty() {
        return size() == 0;
    }


    /***
     *
     * 添加首元素
     */
    void offerFirst(E e);

    /***
     *
     * 删除首元素
     */
    E pollFirst();

    /***
     *
     * 添加尾元素
     */
    void offerLast(E e);

    /***
     *
     * 删除尾元素
     */
    E pollLast();

    /**
     * 迭代器
     */
    default Iterator<E> iterator() {
        throw new UnsupportedOperationException("iterator is not implemented");
    }

    /**
     * 返回首元素
     */
    E peekFirst();

    /**
     * 返回尾元素
     */
    E peekLast();
}
