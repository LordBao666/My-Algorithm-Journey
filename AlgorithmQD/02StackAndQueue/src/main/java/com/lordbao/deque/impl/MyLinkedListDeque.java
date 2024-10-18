package com.lordbao.deque.impl;


import com.lordbao.deque.MyAbstractDeque;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

/**
 * @Author Lord_Bao
 * @Date 2024/10/18 9:51
 * @Version 1.0
 */
@Slf4j
public class MyLinkedListDeque<E> extends MyAbstractDeque<E> {
    /**
     * 元素个数
     */
    private int size;
    /***
     * 头结点
     */
    private final MyDoubleNode head;

    private class MyDoubleNode {
        public E data;
        public MyDoubleNode prev;
        public MyDoubleNode next;

        public MyDoubleNode() {
        }

        public MyDoubleNode(E data, MyDoubleNode prev, MyDoubleNode next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        /**
         * @param i >=-1
         *          <p>
         *          当 i=-1 表示找当前节点，当i=0 表示找下一个节点，当i=1 表示找下下个节点，如此类推
         */
        public MyDoubleNode findForward(int i) {
            int j = -1;
            MyDoubleNode p = this;
            while (j < i) {
                p = p.next;
                j++;
            }
            return p;
        }

        /**
         * @param i >=-1
         *          <p>
         *          当 i=-1 表示找当前节点，当i=0 表示找上一个节点，当i=1 表示找上上个节点，如此类推
         */
        public MyDoubleNode findBackward(int i) {
            int j = -1;
            MyDoubleNode p = this;
            while (j < i) {
                p = p.prev;
                j++;
            }
            return p;
        }

    }

    public MyLinkedListDeque() {
        head = new MyDoubleNode(null, null, null);
        head.prev = head;
        head.next = head;
        size = 0;
    }

    @Override
    public void addFirst(E e) {
        MyDoubleNode newFirst = new MyDoubleNode(e, head, head.next);
        head.next.prev = newFirst;
        head.next = newFirst;
        size++;
    }

    @Override
    public void addLast(E e) {
        MyDoubleNode newLast = new MyDoubleNode(e, head.prev, head);
        head.prev.next = newLast;
        head.prev = newLast;
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            String error = "FAILED! " + getClass().getSimpleName() + " is EMPTY!!!";
            log.error(error);
            throw new IllegalStateException(error);
        }
        E data = head.next.data;
        head.next.next.prev = head;
        head.next = head.next.next;
        size--;
        return data;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            String error = "FAILED! " + getClass().getSimpleName() + " is EMPTY!!!";
            log.error(error);
            throw new IllegalStateException(error);
        }
        E data = head.prev.data;
        head.prev.prev.next = head;
        head.prev = head.prev.prev;
        size--;
        return data;
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
        // i 从0开始 顺数找第i个节点，等价于反数找第 size -1 - i 个节点
        // 假设i=0, size =3, 顺数找第0个节点，等价于反数找第2个节点
        MyDoubleNode p = i < (size >> 1) ? head.findForward(i) : head.findBackward(size - 1 - i);
        return p.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private MyDoubleNode p = head.next;

            @Override
            public boolean hasNext() {
                return p != head;
            }

            @Override
            public E next() {
                E result = p.data;
                p = p.next;
                return result;
            }
        };
    }
}
