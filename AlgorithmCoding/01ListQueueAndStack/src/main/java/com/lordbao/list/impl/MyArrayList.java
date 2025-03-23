package com.lordbao.list.impl;


import com.lordbao.list.MyList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @Author Lord_Bao
 * @Date 2025/3/23 15:33
 * @Version 1.0
 * <p>
 * 底层基于数组的自定义ArrayList
 */
public class MyArrayList<E> implements MyList<E> {

    /**
     * 底层数组的容量
     */
    private int capacity;
    /**
     * 元素个数
     */
    private int size;
    private E[] arr;
    /**
     * 默认的数组容量为8
     */
    private static final int DEFAULT_CAPACITY = 8;


    @SuppressWarnings("unchecked")
    public MyArrayList() {
        capacity = DEFAULT_CAPACITY;
        size = 0;
        arr = (E[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("容量不能为负值");
        }
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        size = 0;
        arr = (E[]) new Object[capacity];
    }


    @SuppressWarnings("unchecked")
    public MyArrayList(E[] arr) {
        //取arr.length的1.5倍 和 DEFAULT_CAPACITY 的最大值充当容量
        capacity = Math.max((arr.length >> 1) + arr.length, DEFAULT_CAPACITY);
        this.arr = (E[]) new Object[capacity];
        System.arraycopy(arr, 0, this.arr, 0, arr.length);
        size = arr.length;
    }


    @Override
    public void clearList() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;//帮助垃圾回收
        }
        //size置为0
        size = 0;
    }

    /**
     * 按照1.5倍进行扩容.
     */
    private void expand() {
        int newCapacity = (capacity >> 1) + capacity;
        resize(newCapacity);
    }


    @Override
    public void insertList(int i, E e) {
        if (i < 0 || i > size) {
            throw new IllegalArgumentException("传入参数 " + i + " 非法,未在0(含)到" + size + "(含)之间");
        }

        if (isFull()) {
            expand();
        }

        //内存级批量复制:下标i...size-1 的元素后移
        System.arraycopy(arr, i, arr, i + 1, size - i);
        //i处插入元素e.
        arr[i] = e;
        size++;

    }

    /**
     * 缩容一半
     */
    private void shrink() {
        resize(capacity / 2);
    }

    @Override
    public E deleteList(int i) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("传入参数 " + i + " 非法,未在0(含)到" + (size - 1) + "(含)之间");
        }
        E ans = arr[i];

        //内存级批量复制:下标i+1...size-1 的元素前移
        System.arraycopy(arr, i + 1, arr, i, size - (i + 1));
        arr[size - 1] = null;//帮助垃圾回收
        size--;
        //如果使用比小于1/4 并且当前容量缩容一半之后大于等于默认容量,则缩容
        if (size < capacity / 4 && capacity / 2 >= DEFAULT_CAPACITY) {
            shrink();
        }
        return ans;
    }


    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newArr = (E[]) new Object[newCapacity];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
        capacity = newCapacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int locateElem(E e) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(e, arr[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E getElem(int i) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("传入参数 " + i + " 非法,未在0(含)到" + (size - 1) + "(含)之间");
        }
        return arr[i];
    }

    @Override
    public void addFirst(E e) {
        insertList(0, e);
    }

    @Override
    public E removeFirst() {
        return deleteList(0);
    }

    @Override
    public void addLast(E e) {
        insertList(size, e);
    }

    @Override
    public E removeLast() {
        return deleteList(size - 1);
    }


    public boolean isFull() {
        return capacity == size;
    }


    private class MyArrayListIterator implements Iterator<E> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E cur = arr[index];//获取当前索引的元素
            index++;//索引后移
            return cur;
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(arr[i]);
            if (i < size - 1) sb.append(", ");
        }
        return sb.append("]").toString();
    }

}
