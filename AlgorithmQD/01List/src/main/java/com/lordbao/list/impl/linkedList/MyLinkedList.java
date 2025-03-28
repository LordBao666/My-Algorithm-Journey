package com.lordbao.list.impl.linkedList;


import com.lordbao.list.MyList;
import com.lordbao.list.utils.LinkedListInitType;
import com.lordbao.list.utils.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Objects;

/**
 * @Author Lord_Bao
 * @Date 2024/10/11 17:31
 * @Version 1.0
 * <p>
 * 单链表
 */
@Slf4j
public class MyLinkedList<E> implements MyList<E> {

    /**
     * 头结点
     */
    private MyNode head;

    /**
     * 结点个数
     */
    private int size;

    /**
     * 结点内部类
     */
    private class MyNode {
        public E data;
        public MyNode next;

        public MyNode() {
        }

        public MyNode(E data, MyNode next) {
            this.data = data;
            this.next = next;
        }


        /**
         * 以当前节点为起始节点,令它的起始顺序为-1，找到第i个节点。
         * 注意，这里假设寻找的节点必然是存在的，不会报空指针错误。
         * 注意，假定 i>=-1
         * <p>
         * 当i=-1时，表示寻找当前节点; 当i=0时表示寻找当前节点的下一个节点，如此类推
         */
        public MyNode findNodeAt(int i) {
            MyNode p = this;
            int j = -1;
            while (j < i) {
                p = p.next;
                j++;
            }
            return p;
        }
    }

    @SuppressWarnings("unchecked")
    public MyLinkedList() {
        this((E[]) new Object[]{});
    }

    /***
     * 默认采用尾插法
     */
    public MyLinkedList(E[] arr) {
        this(LinkedListInitType.TAIL_INSERT, arr);
    }

    /**
     * @param type 尾插法还是头插法
     * @param arr  将要插入的数据集
     */
    public MyLinkedList(LinkedListInitType type, E[] arr) {
        if (type == LinkedListInitType.HEAD_INSERT) {
            headInsert(arr);
        } else if (type == LinkedListInitType.TAIL_INSERT) {
            tailInsert(arr);
        }
    }

    /***
     * 默认采用尾插法
     */
    public MyLinkedList(MyList<E> list) {
        this(LinkedListInitType.TAIL_INSERT, list);
    }

    /**
     * @param type 尾插法还是头插法
     * @param arr  将要插入的数据集
     */
    public MyLinkedList(LinkedListInitType type, MyList<E> arr) {
        if (type == LinkedListInitType.HEAD_INSERT) {
            headInsert(arr);
        } else if (type == LinkedListInitType.TAIL_INSERT) {
            tailInsert(arr);
        }
    }


    /**
     * @param list 将进行尾插法的list
     *             尾插建表
     */
    private void tailInsert(MyList<E> list) {
        head = new MyNode(null, null);
        MyNode p = head;
        for (E ele : list) {
            MyNode temp = new MyNode(ele, null);
            p.next = temp;
            p = temp;
        }
        size = list.listLength();
    }

    /**
     * @param list 将进行头插法的list
     *             头插建表
     */
    private void headInsert(MyList<E> list) {
        head = new MyNode(null, null);
        for (E ele : list) {
            head.next = new MyNode(ele, head.next);
        }
        size = list.listLength();
    }


    /**
     * @param arr 将进行尾插法的arr
     *            尾插建表
     */
    private void tailInsert(E[] arr) {
        head = new MyNode(null, null);
        MyNode p = head;
        for (E ele : arr) {
            MyNode temp = new MyNode(ele, null);
            p.next = temp;
            p = temp;
        }
        size = arr.length;
    }

    /**
     * @param arr 将进行头插法的list
     *            头插建表
     */
    private void headInsert(E[] arr) {
        head = new MyNode(null, null);
        for (E ele : arr) {
            head.next = new MyNode(ele, head.next);
        }
        size = arr.length;
    }


    @Override
    public Status clearList() {
        head.next = null;
        size = 0;
        return Status.OK;
    }

    @Override
    public Status insertList(int i, E e) {
        if (i < 0 || i > size) {
            log.error("The position {} is out of index between 0 and {}", i, size);
            return Status.OVERFLOW;
        }
        //找到第i个节点的前驱(i从0开始)
        MyNode p = head.findNodeAt(i - 1);
        p.next = new MyNode(e, p.next);
        size++;
        return Status.OK;
    }

    @Override
    public E deleteList(int i) {
        if (i < 0 || i > size - 1) {
            log.error("The position {} is out of index between 0 and {}", i, size - 1);
            return null;
        }
        //找到第i个节点的前驱(i从0开始)
        MyNode p = head.findNodeAt(i - 1);
        E result = p.next.data;
        p.next = p.next.next;
        size--;
        return result;
    }

    @Override
    public int listLength() {
        return size;
    }

    @Override
    public int locateElem(E e) {
        MyNode p = head.next;
        int i = 0;
        while (p != null) {
            if (Objects.equals(p.data, e)) {
                return i;
            }
            i++;
            p = p.next;
        }
        log.info("{} is not found!", e);
        return -1;
    }

    /**
     * 返回位置i处的元素. 注意，如果下标不对仍然会返回null
     * 因此请查看日志信息.
     */
    @Override
    public E getElem(int i) {
        if (i < 0 || i > size - 1) {
            log.error("The position {} is out of index between 0 and {}", i, size - 1);
            return null;
        }
        return head.findNodeAt(i).data;
    }


    @Override
    public String toString() {
        if (isEmpty()) {
            return "MyLinkedList {}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("MyLinkedList { ");
        MyNode p = head.next;


        while (p.next != null) {
            sb.append(p.data).append(",");
            p = p.next;
        }
        sb.append(p.data).append(" }");
        return sb.toString();
    }

    @Override
    public Status addFirst(E e) {
        return insertList(0, e);
    }

    @Override
    public E removeFirst() {
        return deleteList(0);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private MyNode p = head.next;

            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public E next() {
                E e = p.data;
                p = p.next;
                return e;
            }
        };
    }
}
