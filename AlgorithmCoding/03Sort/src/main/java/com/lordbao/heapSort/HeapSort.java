package com.lordbao.heapSort;


import com.lordbao.tree.heap.MaxHeap;
import com.lordbao.tree.heap.MyPriorityQueue;

import java.util.Comparator;

/**
 * @Author Lord_Bao
 * @Date 2025/4/20 9:49
 * @Version 1.0
 */
public class HeapSort {
    @SuppressWarnings("unchecked")
    public static <T> T[] sort(T[] nums) {
        //顺序排序,那么构建大根堆
        Comparator<T> comp = (o1, o2) -> {
            Comparable<T> newO2 = (Comparable<T>) (o2);
            return newO2.compareTo(o1);
        };
        return sort(nums, comp);
    }

    //如果是利用堆进行顺序排序，那么就是构建大根堆，如果逆序排序，那么就是构建小根堆。
    //也就是说你的comp实际上是反着来的.
    public static <T> T[] sort(T[] nums, Comparator<T> comp) {
        MyPriorityQueue<T> queue = new MyPriorityQueue<>(nums, comp);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[n - i - 1] = queue.poll();
        }
        return nums;
    }


    public static int[] sort(int[] nums) {
        MaxHeap heap = new MaxHeap(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[n - i - 1] = heap.poll();
        }
        return nums;
    }

}
