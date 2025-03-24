package com.lordbao.list.impl;


import com.lordbao.list.MyDeque;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @Author Lord_Bao
 * @Date 2025/3/24 14:48
 * @Version 1.0
 */
public class MyArrayDeque<E> implements MyDeque<E> {

    /**
     * 头部待插入位置
     */
    private int head;
    /**
     * 尾部待插入位置
     */
    private int tail;
    private E[] arr;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 8;

    @SuppressWarnings("unchecked")
    public MyArrayDeque() {
        this.capacity = DEFAULT_CAPACITY;
        this.arr = (E[]) new Object[this.capacity];
        size = 0;
        head = this.capacity - 1;
        tail = 0;
    }

    @SuppressWarnings("unchecked")
    public MyArrayDeque(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("容量不能为空");
        }
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        this.arr = (E[]) new Object[this.capacity];
        size = 0;
        head = this.capacity - 1;
        tail = 0;
    }

    @SuppressWarnings("unchecked")
    public MyArrayDeque(E[] arr) {
        this.capacity = Math.max((arr.length >> 1) + arr.length, DEFAULT_CAPACITY);
        this.arr = (E[]) new Object[this.capacity];
        System.arraycopy(arr, 0, this.arr, 0, arr.length);
        size = arr.length;
        head = this.capacity - 1;
        tail = arr.length % capacity;//实际上arr.length也可以,不会出现越界
    }

    @Override
    public void clear() {
        for (int i = (head + 1) % capacity, count = 0; count != size; i = (i + 1) % capacity, count++) {
            arr[i] = null;//帮助垃圾回收
        }
        size = 0;
        head = this.capacity - 1;
        tail = 0;
    }


    @Override
    public int size() {
        return size;
    }

    private boolean isFull() {
        return size == capacity;
    }

    /**
     * 按照1.5倍扩容
     */
    private void expand() {
        int newCapacity = (capacity >> 1) + capacity;
        resize(newCapacity);
    }


    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newArr = (E[]) new Object[newCapacity];
        //i用于扫描新数组,j用于扫描旧数组,count用于记录扫描的元素个数
        for (int i = 0, j = (head + 1) % capacity, count = 0; count != size; i = (i + 1) % newCapacity, j = (j + 1) % capacity, count++) {
            newArr[i] = arr[j];
        }
        arr = newArr;
        capacity = newCapacity;
        head = newCapacity - 1;
        tail = size;
    }

    @Override
    public void offerFirst(E e) {
        if (isFull()) {
            expand();
        }
        arr[head] = e;
        head = (head - 1 + capacity) % capacity;
        size++;
    }


    /**
     * 是否需要缩容
     * <p>
     * 如果使用比小于1/4 并且当前容量缩容一半之后大于等于默认容量,则缩容
     */
    private boolean isShrinkAble() {
        return size < capacity / 4 && capacity / 2 >= DEFAULT_CAPACITY;
    }

    /**
     * 缩容一半
     */
    private void shrink() {
        int newCapacity = capacity >> 1;
        resize(newCapacity);
    }

    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        head = (head + 1) % capacity;
        E ans = arr[head];
        arr[head] = null;//帮助垃圾回收
        size--;

        if (isShrinkAble()) {
            shrink();
        }

        return ans;
    }


    @Override
    public void offerLast(E e) {
        if (isFull()) {
            expand();
        }
        arr[tail] = e;
        tail = (tail + 1) % capacity;
        size++;
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        tail = (tail - 1 + capacity) % capacity;
        E ans = arr[tail];
        arr[tail] = null;//帮助垃圾回收
        size--;

        if (isShrinkAble()) {
            shrink();
        }

        return ans;
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return arr[(head + 1) % capacity];
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return arr[(tail - 1 + capacity) % capacity];
    }


    private class MyArrayDequeIterator implements Iterator<E> {

        /**
         * 用于扫描ArrayDeque
         */
        private int index = head;
        /**
         * 记录迭代器已经扫描ArrayDeque元素个数
         */
        private int count = 0;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            index = (index + 1) % capacity;
            count++;
            return arr[index];
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new MyArrayDequeIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = (head + 1) % capacity, count = 0; count != size; i = (i + 1) % capacity, count++) {
            sb.append(arr[i]);
            //还没有扫描最后一个元素
            if (count < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
