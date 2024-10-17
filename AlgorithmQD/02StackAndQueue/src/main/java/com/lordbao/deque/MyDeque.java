package com.lordbao.deque;


/**
 * @Author Lord_Bao
 * @Date 2024/10/15 17:18
 * @Version 1.0
 *
 * 双端队列 下标从0开始  下标0 对应首元素 下标1 对应下一个元素，如此类推
 */
public interface MyDeque<E> extends Iterable<E>{
    /**
     * 将元素插入到首部
     */
    public void addFirst(E e);

    /**
     * 将元素插入到尾部
     */
    public void addLast(E e);

    /**
     * 去除双端队列第一个元素，并将其返回。
     */
    public E removeFirst();

    /**
     * 去除双端队列最后一个元素，并将其返回。
     */
    public E removeLast();

    /***
     *
     * 返回双端队列的第一个元素
     */
    public default E getFirst(){
        return get(0);
    }

    /**
     *
     * 得到双端队列的最后一个元素
     */
    public default E getLast(){
        return get(size()-1);
    }

    /**
     * 返回双端队列是否为空
     */
    public default boolean isEmpty(){
        return size()==0;
    }

    /**
     * 返回双端队列的元素个数
     */
    public int size();

    /**
     * 对双端队列进行打印
     */
    public void printDeque();



    /**
     * @param  index 从0开始 0表示首元素，1表示下一个元素，依次类推
     * 返回 index 所指的元素
     */
    public E get(int index);
}
