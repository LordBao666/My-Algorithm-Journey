package com.lordbao.tree.heap;

/**
 * 大根堆
 */
public class MaxHeap {

    private int size;

    private final double[] nums;
    private int capacity;

    //根据nums构建大根堆
    //会存储nums的2倍容量,即1倍用于其他元素的扩充,也就是这是一个静态数组
    //暂时不考虑扩容的事
    public MaxHeap(double[] nums) {
        this.nums = new double[(nums.length<<1)];
        for (int i = 0; i < nums.length; i++) {
            this.nums[i] = nums[i];
        }
        size = nums.length;
        capacity=(nums.length<<1);
        heapify();
    }


    //根据nums构建大根堆
    private void heapify() {
        //从最后一个非叶节点开始下潜
        for (int i = ((size >> 1) - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    //暂时不考虑越界问题
    public void offer(double ele){
        nums[size]=ele;
        //size++ 在siftUp 后面执行是可以的,因为siftUp
        //函数并没有涉及size
        siftUp(size);
        size++;
    }

    //暂时不考虑size=0 调用此函数的问题
    public double poll(){
        double ans = nums[0];
        swap(0,size-1);
        //注意先size--,再siftDown
        size--;
        siftDown(0);
        return ans;
    }

    //暂时不考虑size=0 调用此函数的问题
    public double peek(){
        return nums[0];
    }

    public boolean isEmpty(){
        return size==0;
    }

    //下标为i的节点是否存在左孩子
    private boolean isLeftChildExist(int i) {
        //i*2+1<size
        return ((i << 1) + 1) < size;
    }

    //下标为i的节点是否存在右孩子
    private boolean isRightChildExist(int i) {
        //i*2+2<size
        return ((i << 1) + 2) < size;
    }

    //将坐标i的元素进行siftDown
    private void siftDown(int i) {
        while (i < size) {
            if (isLeftChildExist(i)) {
                double curVal = nums[i];
                double leftChildVal = nums[((i << 1) + 1)];
                double maxChildVal = leftChildVal;

                if (isRightChildExist(i)) {
                    double rightChildVal = nums[((i << 1) + 2)];
                    if (leftChildVal < rightChildVal) {
                        maxChildVal = rightChildVal;
                    }
                }

                if (curVal < maxChildVal) {
                    if (maxChildVal == leftChildVal) {
                        swap(i, ((i << 1) + 1));
                        i = ((i << 1) + 1);//继续下潜
                    } else {//maxChildVal==rightChildVal
                        swap(i, ((i << 1) + 2));
                        i = ((i << 1) + 2);//继续下潜
                    }
                } else {
                    break;
                }
            } else {
                break;
            }

        }
    }




    //将坐标i的元素进行siftUp
    private void siftUp(int i) {
        while (i > 0) {
            int parentIndex = (i - 1) >> 1;
            if (nums[i] > nums[parentIndex]) {
                swap(i, parentIndex);
            } else {
                break;
            }
            i = parentIndex;
        }
    }

    private void swap(int i, int j) {
        double temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}