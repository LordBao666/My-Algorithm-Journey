package com.lordbao.list.impl.sqlList;


import com.lordbao.list.MyList;
import com.lordbao.list.utils.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * @Author Lord_Bao
 * @Date 2024/10/2 23:03
 * @Version 1.0
 * <p>
 * 动态顺序表,支持动态缩容和扩容，支持泛型
 */
@Slf4j
public class MySqlList<E> implements MyList<E> {
    /**
     * 容量
     */
    private int capacity = 8;
    private E[] data;
    /**
     * 实际大小
     */
    private int size;

    //初始化片段：每个构造函数都先调用此函数
    {
        initList();
    }

    public MySqlList() {
    }

    public MySqlList(MyList<E> list) {
        for (E e : list) {
            addLast(e);
        }
    }

    public MySqlList(E[] arr) {
        for (E e : arr) {
            addLast(e);
        }
    }

    public boolean isFull() {
        return size == capacity;
    }

    @SuppressWarnings("unchecked")
    public void initList() {
        data = (E[]) new Object[capacity];
        Arrays.fill(data, null);
        size = 0;
    }

    @Override
    public Status clearList() {
        for (int i = 0; i < size; i++) {
            /*帮助垃圾回收*/
            data[i] = null;
        }
        size = 0;
        return Status.OK;
    }

    @Override
    public Status insertList(int i, E e) {
        if (i < 0 || i > size) {
            log.error("The position {} is out of index between 0 and {}", i, size);
            return Status.OVERFLOW;
        }
        if (isFull()) {
            expand();
        }
        for (int j = size - 1; j >= i; j--) {
            data[j + 1] = data[j];
        }
        data[i] = e;
        size++;
        return Status.OK;
    }

    /**
     * 容量从oldCapacity 变化到 newCapacity
     * 注意，始终假定size是小于等于newCapacity
     */
    @SuppressWarnings("unchecked")
    private void resize(int oldCapacity, int newCapacity) {
        log.info("The list resizes from {} to {}", oldCapacity, newCapacity);
        log.info("The list is {}", this);

        E[] newData = (E[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
        capacity = newCapacity;
    }

    private void expand() {
        //扩容1.5倍
        int newCapacity = capacity + (capacity >> 1);
        resize(capacity, newCapacity);
    }

    @Override
    public E deleteList(int i) {
        if (i < 0 || i > size - 1) {
            log.error("The position {} is out of index between 0 and {}", i, size - 1);
            return null;
        }
        E result = data[i];
        for (int j = i + 1; j < size; j++) {
            data[j - 1] = data[j];
        }
        data[size - 1] = null;//帮助垃圾回收
        size--;
        shrink();
        return result;
    }

    /**
     * 当元素的利用率低于1/4 且 容量个数大于8个时，实行缩容策略
     * 将容量缩小为1/2.
     */
    private void shrink() {
        if (size * 4 < capacity && capacity > 8) {
            int newCapacity = capacity >> 1;
            resize(capacity, newCapacity);
        }
    }

    @Override
    public int listLength() {
        return size;
    }

    @Override
    public int locateElem(E e) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], e)) {
                return i;
            }
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
        return data[i];
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "MySqlList {}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("MySqlList { ");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]).append(",");
        }
        sb.append(data[size - 1]).append(" }");
        return sb.toString();
    }


    /**
     * 时间复杂度为O(n) 效率不高
     */
    @Override
    public Status addFirst(E e) {
        return insertList(0, e);
    }

    @Override
    public Status addLast(E e) {
        return insertList(size, e);
    }

    /**
     * 时间复杂度为O(n) 效率不高
     */
    @Override
    public E removeFirst() {
        return deleteList(0);
    }

    @Override
    public E removeLast() {
        return deleteList(size - 1);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public E next() {
                return data[count++];
            }
        };
    }
}
