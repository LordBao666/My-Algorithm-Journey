package com.lordbao.deque.impl;


import com.lordbao.deque.MyAbstractDeque;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

/**
 * @Author Lord_Bao
 * @Date 2024/10/15 17:19
 * @Version 1.0
 * <p>
 * 基于数组的双端队列
 */
@Slf4j
public class MyArrayDeque<E> extends MyAbstractDeque<E> {
    /**
     * 容量
     */
    private int capacity = 8;

    /**
     * 底层数组
     */
    private E[] data;

    /**
     * 实际大小
     */
    private int size;

    /***
     * 反向：下一个插入的位置
     */
    private int head;
    /***
     * 正向：下一个插入的位置
     */
    private int tail;

    @SuppressWarnings("unchecked")
    public MyArrayDeque() {
        data = (E[]) new Object[capacity];
        size = 0;
        //只要使得head 在 tail的前一个位置即可
        head = capacity >> 1;
        tail = (capacity >> 1) + 1;
    }


    private boolean isFull() {
        return size == capacity;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        //p,q分别指向data 和 newData的首元素
        int p = (head + 1) % capacity;
        int q = (head + 1) % newCapacity;
        int count = 0;
        while (count != size) {
            newData[q] = data[p];
            p = (p + 1) % capacity;
            q = (q + 1) % newCapacity;
            count++;
        }
        capacity = newCapacity;
        data = newData;
        //head未变,修改tail
        tail = q;
    }


    private void expand() {
        if (isFull()) {
            //按照1.5 倍进行扩容
            int newCapacity = capacity + (capacity >> 1);
            resize(newCapacity);
        }
    }


    @Override
    public void addFirst(E e) {
        expand();
        data[head] = e;
        head = (head - 1 + capacity) % capacity;
        size++;
    }

    @Override
    public void addLast(E e) {
        expand();
        data[tail] = e;
        tail = (tail + 1) % capacity;
        size++;
    }


    private void shrink() {
        //如果有效占比小于 .25 并且 容量大于8,则 缩容一半
        if (size * 4 < capacity && capacity > 8) {
            int newCapacity = (capacity >> 1);
            resize(newCapacity);
        }
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            String error = "FAILED! " + getClass().getSimpleName() + " is EMPTY!!!";
            log.error(error);
            throw new IllegalStateException(error);
        }

        head = (head + 1) % capacity;
        E result = data[head];
        data[head] = null;//帮助垃圾回收
        size--;
        shrink();
        return result;
    }


    @Override
    public E removeLast() {
        if (isEmpty()) {
            String error = "FAILED! " + getClass().getSimpleName() + " is EMPTY!!!";
            log.error(error);
            throw new IllegalStateException(error);
        }

        tail = (tail - 1 + capacity) % capacity;
        E result = data[tail];
        data[tail] = null;//帮助垃圾回收
        size--;
        shrink();
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int i) {
        if (isEmpty()) {
            String error = "FAILED! " + getClass().getSimpleName() + " is EMPTY!!!";
            log.error(error);
            throw new IndexOutOfBoundsException(error);
        }
        if (i < 0 || i >= size) {
            String error = "FAILED! " + "index is not between 0 and " + (size - 1);
            log.error(error);
            throw new IndexOutOfBoundsException(error);
        }
        return data[(head + 1 + i) % capacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int count = 0;
            private int p = head;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                count++;
                p = (p + 1) % capacity;
                return data[p];
            }
        };
    }
}
