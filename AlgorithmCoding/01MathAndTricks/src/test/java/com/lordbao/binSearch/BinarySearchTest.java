package com.lordbao.binSearch;

import com.lordbao.utils.ArrayHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @Author Lord_Bao
 * @Date 2024/12/10 10:09
 * @Version 1.0
 */
class BinarySearchTest {

    /**
     *
     * @param nums 有序数组
     * @param target 目标元素
     * @return 找到目标元素在有序数组的下标, 如果不存在, 则返回-1
     * 本函数用于测试此函数: {@link BinarySearch#binSearch0(int[], int)}
     */
    public static int linearSearch0(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }


    /**
     *
     * @param nums 非空有序数组
     * @param target 目标元素
     * @return 返回有序数组中nums[i] <= target的最大i (不存在比target小于等于的元素,则返回-1)
     * 本函数用于测试此函数: {@link BinarySearch#binSearch1(int[], int)}
     */
    public static int linearSearch1(int[] nums, int target) {
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] <= target) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param nums 非空有序数组
     * @param target 目标元素
     * @return 返回有序数组中nums[i] < target的最大i (不存在比target小的元素,则返回-1)
     * 本函数用于测试此函数: {@link BinarySearch#binSearch2(int[], int)}
     */
    public static int linearSearch2(int[] nums, int target) {
        int ans = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < target) {
                ans = i;
            } else {
                break;
            }
        }
        return ans;
    }

    /**
     * 本函数用于测试 {@link  BinarySearch#binSearch0(int[], int)}
     */
    @Test
    void binSearch0() {

        //对数器测试
        int testTimes = 100;
        int lo = 0, hi = 100, n = 10;//[0,100]产生10个不重复的元素
        for (int i = 0; i < testTimes; i++) {

            //每次测试产生一个数组
            int[] arr = ArrayHelper.randomDistinctOrderArray(lo, hi, n);


            //区间范围存在的值
            int target1 = arr[0];
            int target2 = arr[arr.length - 1];
            int target3 = arr[arr.length >> 1];
            int target4 = arr[(int) (Math.random() * arr.length)];//产生0到arr.length-1

            //区间范围不存在的值
            int target5 = lo - 1;
            int target6 = hi + 1;

            Assertions.assertEquals(linearSearch0(arr, target1), BinarySearch.binSearch0(arr, target1));
            Assertions.assertEquals(linearSearch0(arr, target2), BinarySearch.binSearch0(arr, target2));
            Assertions.assertEquals(linearSearch0(arr, target3), BinarySearch.binSearch0(arr, target3));
            Assertions.assertEquals(linearSearch0(arr, target4), BinarySearch.binSearch0(arr, target4));
            Assertions.assertEquals(linearSearch0(arr, target5), BinarySearch.binSearch0(arr, target5));
            Assertions.assertEquals(linearSearch0(arr, target6), BinarySearch.binSearch0(arr, target6));
        }

    }


    /**
     * 本函数用于测试 {@link  BinarySearch#binSearch1(int[], int)}
     */
    @Test
    void binSearch1() {

        //手写测试案例
        int[] nums = {1, 1, 1, 3, 3, 3, 7};
        Assertions.assertEquals(2, BinarySearch.binSearch1(nums, 1));
        Assertions.assertEquals(5, BinarySearch.binSearch1(nums, 3));
        Assertions.assertEquals(-1, BinarySearch.binSearch1(nums, -1));
        Assertions.assertEquals(6, BinarySearch.binSearch1(nums, 8));


        //对数器测试
        int testTimes = 100;
        int lo = 0, hi = 100, n = 50;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = ArrayHelper.randomOrderArray(lo, hi, n);

            int target1 = arr[0];
            int target2 = arr[arr.length - 1];
            int target3 = arr[0] - 1;
            int target4 = arr[arr.length - 1] + 1;
            int target5 = arr[(int) (Math.random() * arr.length)];

            Assertions.assertEquals(linearSearch1(arr, target1), BinarySearch.binSearch1(arr, target1));
            Assertions.assertEquals(linearSearch1(arr, target2), BinarySearch.binSearch1(arr, target2));
            Assertions.assertEquals(linearSearch1(arr, target3), BinarySearch.binSearch1(arr, target3));
            Assertions.assertEquals(linearSearch1(arr, target4), BinarySearch.binSearch1(arr, target4));
            Assertions.assertEquals(linearSearch1(arr, target5), BinarySearch.binSearch1(arr, target5));
        }
    }


    /**
     * 本函数用于测试 {@link  BinarySearch#binSearch2(int[], int)}
     */
    @Test
    void binSearch2() {
        //手写测试案例
        int[] nums = {1, 1, 1, 3, 3, 3, 7};
        Assertions.assertEquals(-1, BinarySearch.binSearch2(nums, 1));
        Assertions.assertEquals(2, BinarySearch.binSearch2(nums, 3));
        Assertions.assertEquals(5, BinarySearch.binSearch2(nums, 7));
        Assertions.assertEquals(6, BinarySearch.binSearch2(nums, 8));


        //对数器测试
        int testTimes = 100;
        int lo = 0, hi = 100, n = 50;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = ArrayHelper.randomOrderArray(lo, hi, n);

            int target1 = arr[0];
            int target2 = arr[arr.length - 1];
            int target3 = arr[0] + 1;
            int target4 = arr[arr.length - 1] + 1;
            int target5 = arr[(int) (Math.random() * arr.length)];

            Assertions.assertEquals(linearSearch2(arr, target1), BinarySearch.binSearch2(arr, target1));
            Assertions.assertEquals(linearSearch2(arr, target2), BinarySearch.binSearch2(arr, target2));
            Assertions.assertEquals(linearSearch2(arr, target3), BinarySearch.binSearch2(arr, target3));
            Assertions.assertEquals(linearSearch2(arr, target4), BinarySearch.binSearch2(arr, target4));
            Assertions.assertEquals(linearSearch2(arr, target5), BinarySearch.binSearch2(arr, target5));
        }
    }


}