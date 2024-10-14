package com.lordbao.impl.linkedList;


import com.lordbao.MyList;
import com.lordbao.utils.LinkedListInitType;
import com.lordbao.utils.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Objects;

/**
 * @Author Lord_Bao
 * @Date 2024/10/13 15:02
 * @Version 1.0
 *
 * 双向循环链表
 */
@Slf4j
public class MyDoubleLinkedCircularList<E>  implements MyList<E>{
    /**结点个数*/
    private int size;
    /**头结点*/
    private MyDoubleNode head;

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
                E data = p.data;
                p = p.next;
                return data;
            }
        };
    }


    public class MyDoubleNode{
        public E data;
        public MyDoubleNode prev;
        public MyDoubleNode next;

        public MyDoubleNode(){
        }

        public MyDoubleNode(E data,  MyDoubleNode prev,MyDoubleNode next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        /**
         * 以当前节点为起始节点,令它的起始顺序为-1，!!! 正向 !!! 找到第i个节点。
         * 注意，这里假设寻找的节点必然是存在的。
         * 注意，假定 i>=-1
         * <p>
         * 当i=-1时，表示寻找当前节点; 当i=0时表示寻找当前节点的下一个节点，如此类推
         */
        public MyDoubleNode findNodeForward(int i) {
            MyDoubleNode p = this;
            int j = -1;
            while (j < i) {
                p = p.next;
                j++;
            }
            return p;
        }

        /**
         * 以当前节点为起始节点,令它的起始顺序为-1，!!! 逆向 !!! 找到第i个节点。
         * 注意，这里假设寻找的节点必然是存在的。
         * 注意，假定 i>=-1
         * <p>
         * 当i=-1时，表示寻找当前节点; 当i=0时表示寻找当前节点的前一个节点，如此类推
         */
        public MyDoubleNode findNodeBackward(int i) {
            MyDoubleNode p = this;
            int j = -1;
            while (j < i) {
                p = p.prev;
                j++;
            }
            return p;
        }


    }


    @SuppressWarnings("unchecked")
    public MyDoubleLinkedCircularList(){
        this((E[]) new Object[]{});
    }

    /***
     * 默认采用尾插法
     */
    public MyDoubleLinkedCircularList(E [] arr){
        this(LinkedListInitType.TAIL_INSERT,arr);
    }

    /**
     * @param type 尾插法还是头插法
     * @param arr 将要插入的数据集
     */
    public MyDoubleLinkedCircularList(LinkedListInitType type, E [] arr) {
        if (type == LinkedListInitType.HEAD_INSERT) {
            headInsert(arr);
        } else if (type == LinkedListInitType.TAIL_INSERT) {
            tailInsert(arr);
        }
    }


    /**
     * @param arr 将进行尾插法的arr
     *             尾插建表
     */
    private void tailInsert(E [] arr) {
        head = new MyDoubleNode(null,null, null);
        head.prev=head;
        head.next=head;
        for (E ele : arr) {
            MyDoubleNode q = new MyDoubleNode(ele,head.prev,head);
            head.prev.next=q;
            head.prev=q;
        }
        size = arr.length;
    }

    /**
     * @param arr 将进行头插法的arr
     *             头插建表
     */
    private void headInsert(E [] arr) {
        head = new MyDoubleNode(null,null, null);
        head.prev=head;
        head.next=head;

        for (E ele : arr) {
            MyDoubleNode q = new MyDoubleNode(ele,head,head.next);
            head.next.prev=q;
            head.next=q;
        }
        size = arr.length;
    }

    @Override
    public Status clearList() {
        head.next=head;
        head.prev=head;
        size=0;
        return Status.OK;
    }

    @Override
    public Status insertList(int i, E e) {
        if (i < 0 || i > size) {
            log.error("The position {} is out of index between 0 and {}", i, size);
            return Status.OVERFLOW;
        }

        //目标找第i-1个节点
        //我们的下标从0开始, 那么顺着找第i-1个节点，等价于逆着找第size-i 个节点
        //比如假设 size=3, i=1, 那么顺着找第0个节点，等价于逆着找第2个节点.
        MyDoubleNode p = (i-1)<(size>>1)? head.findNodeForward(i-1): head.findNodeBackward(size-i);

        MyDoubleNode temp = new MyDoubleNode(e, p, p.next);
        p.next.prev=temp;
        p.next=temp;
        size++;
        return Status.OK;
    }

    @Override
    public E deleteList(int i) {
        if (i < 0 || i > size - 1) {
            log.error("The position {} is out of index between 0 and {}", i, size - 1);
            return null;
        }
        //目标找第i-1个节点
        //我们的下标从0开始, 那么顺着找第i-1个节点，等价于逆着找第size-i 个节点
        //比如假设 size=3, i=1, 那么顺着找第0个节点，等价于逆着找第2个节点.
        MyDoubleNode p = (i-1)<(size>>1)? head.findNodeForward(i-1): head.findNodeBackward(size-i);
        E result = p.next.data;
        p.next.next.prev=p;
        p.next=p.next.next;
        size--;
        return result;
    }

    @Override
    public int listLength() {
        return size;
    }

    @Override
    public int locateElem(E e) {
        MyDoubleNode p = head.next;
        int i = 0;
        while (p != head) {
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

        return  i < (size>>1)? head.findNodeForward(i).data:head.findNodeBackward(size-i-1).data;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "MyDoubleLinkedCircularList {}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("MyDoubleLinkedCircularList { ");
        MyDoubleNode p = head.next;


        while (p.next != head) {
            sb.append(p.data).append(",");
            p = p.next;
        }
        sb.append(p.data).append(" }");
        return sb.toString();
    }

    @Override
    public Status addFirst(E e){
        return insertList(0,e);
    }

    @Override
    public E removeFirst(){
        return deleteList(0);
    }

    @Override
    public Status addLast(E e){
        return insertList(size,e);
    }

    @Override
    public E removeLast(){
        return deleteList(size-1);
    }
}
