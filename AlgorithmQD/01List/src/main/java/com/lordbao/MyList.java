package com.lordbao;

import com.lordbao.utils.Status;

import java.util.Iterator;

/**
 * @Author Lord_Bao
 * @Date 2024/10/2 21:09
 * @Version 1.0
 * <p>
 * MyList的抽象接口, 注意下标都是从0开始
 */
public interface MyList<E> extends Iterable<E> {


    Status clearList();

    /**
     * 在位置i处插入元素e
     */
    Status insertList(int i, E e);

    /**
     * 删除位置i处的元素，并将其返回
     */
    E deleteList(int i);

    int listLength();

    default boolean isEmpty() {
        return listLength() == 0;
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
    default Status addFirst(E e) {
        throw new UnsupportedOperationException("addFirst is not implemented");
    }

    /***
     *
     * 删除首元素
     */
    default E removeFirst() {
        throw new UnsupportedOperationException("removeFirst is not implemented");
    }

    /***
     *
     * 添加尾元素
     */
    default Status addLast(E e) {
        throw new UnsupportedOperationException("addLast is not implemented");
    }

    /***
     *
     * 删除尾元素
     */
    default E removeLast() {
        throw new UnsupportedOperationException("removeLast is not implemented");
    }

    default Iterator<E> iterator() {
        throw new UnsupportedOperationException("iterator is not implemented");
    }
}
