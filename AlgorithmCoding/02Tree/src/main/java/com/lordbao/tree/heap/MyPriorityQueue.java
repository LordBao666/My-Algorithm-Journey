package com.lordbao.tree.heap;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 默认小根堆
 */
@SuppressWarnings("unchecked")
public class MyPriorityQueue<E> {

    private int size;
    //默认大小为11
    private static final int DEFAULT_CAPACITY = 11;
    private Object[] queue;
    private final Comparator<E> comparator;

    public MyPriorityQueue(int capacity, Comparator<E> comparator) {
        if (capacity < 1) {
            throw new IllegalArgumentException("容量不能为负数或0");
        }
        queue = new Object[capacity];
        this.comparator = comparator;
        size = 0;
    }

    public MyPriorityQueue(int capacity) {
        this(capacity, null);
    }

    public MyPriorityQueue(Comparator<E> comparator) {
        this(DEFAULT_CAPACITY, comparator);
    }

    public MyPriorityQueue() {
        this(DEFAULT_CAPACITY, null);
    }


    public MyPriorityQueue(E[] nums, Comparator<E> comp) {
        //传入的nums为空
        if (nums == null || nums.length == 0) {
            queue = new Object[DEFAULT_CAPACITY];
            this.comparator = comp;
            size = 0;
            return;
        }

        //检查是否存储NULL,若存在则抛出异常
        for (E num : nums) {
            if (num == null) {
                throw new NullPointerException();
            }
        }
        this.queue = Arrays.copyOf(nums, nums.length);
        this.comparator = comp;
        size = queue.length;
        heapify();
    }


    public MyPriorityQueue(E[] nums) {
        this(nums, null);
    }


    //建立小根堆, O(n)时间复杂度
    private void heapify() {
        //从最后一个非叶节点开始下潜
        if (comparator != null) {
            for (int i = ((size >> 1) - 1); i >= 0; i--) {
                siftDownUsingComparator(i, (E) queue[i], queue, size, comparator);
            }
        } else {
            for (int i = ((size >> 1) - 1); i >= 0; i--) {
                siftDownComparable(i, queue[i], queue, size);
            }
        }
    }

    private void grow() {
        //容量小于64时,每次扩容增长2,大于等于64时,按1.5扩容
        int newCapacity = queue.length < 64 ? queue.length + 2 : queue.length + (queue.length >> 1);
        queue = Arrays.copyOf(queue, newCapacity);
    }


    public boolean offer(E ele) {
        if (ele == null)
            throw new NullPointerException();

        if (size >= queue.length) {
            grow();
        }

        siftUp(size, ele);
        size++;
        return true;
    }


    /**
     * 假设index 不会越界, 即 0<= index < queue.length
     * 以index为基准,进行上浮,将ele放置在合适的位置
     */
    private void siftUp(int index, E ele) {
        if (comparator != null) {
            siftUpUsingComparator(index, ele, queue, comparator);
        } else {
            siftUpComparable(index, ele, queue);
        }
    }


    private static <T> void siftUpUsingComparator(int index, T ele, Object[] queue, Comparator<T> comp) {
        while (index > 0) {
            int parent = (index - 1) >> 1;
            T parentEle = (T) queue[parent];
            if (comp.compare(ele, parentEle) < 0) {//ele 更小,则需要上浮
                queue[index] = parentEle;
                index = parent;
            } else {
                break;
            }
        }
        queue[index] = ele;
    }


    private static <T> void siftUpComparable(int index, T ele, Object[] queue) {
        Comparable<T> newEle = (Comparable<T>) ele;
        while (index > 0) {
            int parent = (index - 1) >> 1;
            T parentEle = (T) queue[parent];
            if (newEle.compareTo(parentEle) < 0) {//newEle 更小,则需要上浮
                queue[index] = parentEle;
                index = parent;
            } else {
                break;
            }
        }
        queue[index] = ele;
    }


    public E poll() {
        E ans = (E) queue[0];
        if (ans != null) {//说明优先级队列非空
            queue[0] = queue[--size];
            E top = (E) queue[0];
            if (size > 0) {
                if (comparator != null) {
                    siftDownUsingComparator(0, top, queue, size, comparator);
                } else {
                    siftDownComparable(0, top, queue, size);
                }
            }
            queue[size] = null;//帮助垃圾回收
        }
        return ans;
    }


    public E peek() {
        return (E) queue[0];
    }


    /**
     * 以index为基准,只针对queue的前n个元素进行下潜,将ele插入到合适的位置.
     */
    private static <T> void siftDownUsingComparator(int index, T ele, Object[] queue, int n, Comparator<T> comp) {
        int firstLeaf = n >> 1;//第一个叶子节点
        while (index < firstLeaf) {
            //找到左孩子和右孩子中较小的孩子
            int child = (index << 1) + 1;
            Object e = queue[child];
            if (child + 1 < n && comp.compare((T) queue[child + 1], (T) e) < 0) {//右孩子存在,并且更小
                child = child + 1;
                e = queue[child];
            }
            if (comp.compare((T) e, ele) < 0) {//ele更大,则需要继续下潜
                queue[index] = e;
                index = child;
            } else {
                break;
            }
        }
        queue[index] = ele;
    }

    /**
     * 以index为基准,只针对queue的前n个元素进行下潜,将ele插入到合适的位置.
     */
    private static <T> void siftDownComparable(int index, T ele, Object[] queue, int n) {
        Comparable<T> newEle = (Comparable<T>) ele;
        int firstLeaf = n >> 1;//第一个叶子节点

        while (index < firstLeaf) {
            //找到左孩子和右孩子中较小的孩子
            int child = (index << 1) + 1;
            Object e = queue[child];
            if (child + 1 < n && ((Comparable<T>) queue[child + 1]).compareTo((T) e) < 0) {//右孩子存在,并且更小
                child = child + 1;
                e = queue[child];
            }
            if (newEle.compareTo((T) e) > 0) {//newEle更大,则需要继续下潜
                queue[index] = e;
                index = child;
            } else {
                break;
            }
        }
        queue[index] = ele;

    }


    public boolean isEmpty() {
        return size == 0;
    }
}