package com.lordbao.list.impl;


import com.lordbao.list.MyDeque;
import com.lordbao.list.MyList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @Author Lord_Bao
 * @Date 2025/3/24 17:48
 * @Version 1.0
 */
public class MyLinkedList<E> implements MyList<E>, MyDeque<E> {

    private class MyNode {
        private MyNode prev;
        private MyNode next;
        private E data;

        public MyNode(E data) {
            this.data = data;
        }

        public MyNode() {
        }
    }

    private final MyNode head;
    private final MyNode tail;
    private int size;

    public MyLinkedList() {
        head = new MyNode();
        tail = new MyNode();
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public MyLinkedList(E[] arr) {
        this();
        for (E e : arr) {
            offerLast(e);
        }
    }


    /**
     * 在节点后面插入元素e,此处假定p的next不为null.
     */
    private void insertNodeAfter(MyNode p, E e) {
        MyNode q = new MyNode(e);
        p.next.prev = q;
        q.next = p.next;
        q.prev = p;
        p.next = q;
    }

    /**
     * 在节点前面插入元素e,此处假定p的prev不为null.
     */
    private void insertNodeBefore(MyNode p, E e) {
        MyNode q = new MyNode(e);
        p.prev.next = q;
        q.prev = p.prev;
        q.next = p;
        p.prev = q;
    }

    /**
     * 假定p.prev 和 p.next 均不为null,此方法删除p,并返回p.data
     */
    private E deleteNode(MyNode p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        return p.data;
    }

    @Override
    public void clear() {
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    @Override
    public void offerFirst(E e) {
        size++;
        insertNodeAfter(head, e);
    }


    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        return deleteNode(head.next);
    }

    @Override
    public void offerLast(E e) {
        size++;
        insertNodeBefore(tail, e);
    }

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        return deleteNode(tail.prev);
    }


    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return head.next.data;
    }

    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return tail.prev.data;
    }

    @Override
    public void clearList() {
        clear();
    }

    /**
     * 返回第i个节点
     * 此处假定  0<=i <=size-1
     */
    private MyNode indexOf(int i) {
        MyNode p = head.next;
        for (int j = 0; j < i; j++) {
            p = p.next;
        }
        return p;
    }

    @Override
    public void insertList(int i, E e) {
        if (i < 0 || i > size) {
            throw new IllegalArgumentException("传入参数 " + i + " 非法,未在0(含)到" + size + "(含)之间");
        }
        if (i == size) {
            offerLast(e);
        } else {
            MyNode p = indexOf(i);
            insertNodeBefore(p, e);
            size++;
        }
    }


    @Override
    public E deleteList(int i) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("传入参数 " + i + " 非法,未在0(含)到" + (size - 1) + "(含)之间");
        }
        size--;
        return deleteNode(indexOf(i));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int locateElem(E e) {
        int index = 0;
        for (MyNode p = head.next; p != tail; p = p.next, index++) {
            if (Objects.equals(p.data, e)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public E getElem(int i) {
        if (i < 0 || i >= size) {
            throw new IllegalArgumentException("传入参数 " + i + " 非法,未在0(含)到" + (size - 1) + "(含)之间");
        }
        return indexOf(i).data;
    }

    @Override
    public void addFirst(E e) {
        offerFirst(e);
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        size--;
        return deleteNode(head.next);
    }

    @Override
    public void addLast(E e) {
        offerLast(e);
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        size--;
        return deleteNode(tail.prev);
    }

    private class MyLinkedListIterator implements Iterator<E> {

        private MyNode p = head.next;

        @Override
        public boolean hasNext() {
            return p != tail;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E ans = p.data;
            p = p.next;
            return ans;
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.next.data;
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.prev.data;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (MyNode p = head.next; p != tail; p = p.next) {
            sb.append(p.data);
            if (p.next != tail) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }

}
