package com.lordbao.stack;


import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @Author Lord_Bao
 * @Date 2025/4/14 9:28
 * @Version 1.0
 *
 * 此处单调队列以 找某个区间的 最大值为例,
 * 单调队列从队首到队尾是严格的大到小, 但存储的是目标数组的下标，而非实际值
 *
 * 如果是 找某个区间的最小值, 那么单调队列从队首到队尾就是严格的小到大.
 */
public class MonotoneQueue<T extends Comparable<T>> {

    private final LinkedList<Integer> dequeue;
    private final T [] arr;//单调队列研究的数组,由外部传入
    private  int rightIndex;//rightIndex指向数组指定区间右侧的下一个位置
    private  int leftIndex;//leftIndex指向数组指定区间的最左侧
    //举例说明rightIndex 和 leftIndex
    //[1,3,4,5], 假设现在的区间是[3,4],那么leftIndex=1,指向的是元素3,rightIndex是3,指向的是元素5


    public MonotoneQueue(T [] arr) {
        dequeue=new LinkedList<>();
        this.arr= arr;
        rightIndex=0;
        leftIndex=0;
    }

    /**
     * 数组右端点向右移动steps.
     */
    public void rightMove(int steps){
        if(steps<0 || rightIndex+steps>arr.length){
            throw new IllegalArgumentException("steps传参错误");
        }
        for (int i = rightIndex; i < rightIndex+steps ; i++) {
            while (!dequeue.isEmpty() && arr[dequeue.getLast()].compareTo(arr[i])<=0){
                dequeue.removeLast();
            }
            dequeue.addLast(i);
        }
        rightIndex=rightIndex+steps;
    }

    //不传参数就是右端点右移一步
    public void rightMove(){
        rightMove(1);
    }


    /**
     * 数组左端点向右移动steps
     */
    public void leftMove(int steps){
        if(steps<0 || leftIndex+steps>rightIndex){
            throw new IllegalArgumentException("steps传参错误");
        }
        while (!dequeue.isEmpty() && dequeue.getFirst()<leftIndex+steps){
            dequeue.removeFirst();
        }
        leftIndex=leftIndex+steps;
    }

    //不传参数就是左端点右移一步
    public void leftMove(){
        leftMove(1);
    }


    //返回单调队列的最大值
    public  T  getMax(){
        if (dequeue.isEmpty()){
            throw new NoSuchElementException("队列为空");
        }
        return arr[dequeue.getFirst()];
    }


}
