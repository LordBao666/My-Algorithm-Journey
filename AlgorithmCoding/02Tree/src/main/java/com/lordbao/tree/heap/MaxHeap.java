package com.lordbao.tree.heap;


import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @Author Lord_Bao
 * @Date 2025/4/20 11:54
 * @Version 1.0
 * 针对int的大根堆
 */
public class MaxHeap {
    private int size;
    private int[] nums;
    private static final int DEFAULT_CAPACITY = 11;

    public MaxHeap() {
        nums = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    public MaxHeap(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("容量不能为0或负数");
        }
        nums = new int[capacity];
        size = 0;
    }

    public MaxHeap(int[] arr) {
        if (arr == null || arr.length == 0) {
            nums = new int[DEFAULT_CAPACITY];
            size = 0;
            return;
        }
        nums = Arrays.copyOf(arr, arr.length);
        size = nums.length;
        heapify();
    }


    private void heapify() {
        //从最后一个非叶节点进行下潜
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i, nums[i], nums, size);
        }
    }


    /**
     * 从index开始, 限定nums的前n个元素,将ele进行下潜
     */
    private static void siftDown(int index, int ele, int[] nums, int n) {
        int firstLeaf = n >> 1;//第1个叶子节点
        while (index < firstLeaf) {
            int childIndex = (index << 1) + 1;
            int child = nums[childIndex];
            if (childIndex + 1 < n && nums[childIndex + 1] > child) {//右孩子存在 并且更大
                childIndex = childIndex + 1;
                child = nums[childIndex];
            }
            if (ele < child) {//ele更小,那么继续下潜
                nums[index] = child;
                index = childIndex;
            } else {
                break;
            }
        }
        nums[index] = ele;
    }

    public boolean offer(int ele) {
        if (size >= nums.length) {
            grow();
        }
        siftUp(size, ele, nums);
        size++;
        return true;
    }

    /**
     * 以index为基准,将ele进行上浮
     */
    private void siftUp(int index, int ele, int[] nums) {
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            int parent = nums[parentIndex];
            if (ele > parent) {//如果ele 更大,那么需要继续上浮
                nums[index] = parent;
                index = parentIndex;
            } else {
                break;
            }
        }
        nums[index] = ele;
    }

    private void grow() {
        int newCapacity = nums.length < 64 ? nums.length + 2 : nums.length + (nums.length >> 1);
        nums = Arrays.copyOf(nums, newCapacity);
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return nums[0];
    }

    public int poll() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int ans = nums[0];
        nums[0] = nums[size - 1];
        siftDown(0, nums[0], nums, size - 1);
        size--;
        return ans;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
