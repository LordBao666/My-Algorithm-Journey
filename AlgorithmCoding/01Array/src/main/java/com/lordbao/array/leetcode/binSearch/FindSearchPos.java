package com.lordbao.array.leetcode.binSearch;


/**
 * @Author Lord_Bao
 * @Date 2024/10/23 10:43
 * @Version 1.0
 * 题目: 搜索插入位置
 * 来源：https://leetcode.cn/leetbook/read/array-and-string/cxqdh/
 * 已整理到笔记
 */
public class FindSearchPos {
    /**
     * 左开右开值域写法
     */
    public int searchInsert1(int[] nums, int target) {
        int len = nums.length;

        //对长度为1时的处理
        if (len == 1) {
            if (target <= nums[0]) {
                return 0;
            } else {
                return 1;
            }
        }
        //保证左开右开值域
        if (target <= nums[0]) {
            return 0;
        }
        if (nums[len - 1] == target) {
            return len - 1;
        }
        if (nums[len - 1] < target) {
            return len;
        }

        int lo = 0, hi = len - 1;
        while (lo + 1 < hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (nums[mid] < target) {
                lo = mid;
            } else if (target < nums[mid]) {
                hi = mid;
            } else {
                return mid;
            }
        }
        //程序执行到这里 必然是 lo+1=hi,并且nums[lo]<target<nums[hi]
        //因此返回hi
        return hi;
    }

    /**
     * 左闭右开值域写法
     */
    public int searchInsert2(int[] nums, int target) {
        int len = nums.length;

        //对长度为1时的处理
        if (len == 1) {
            if (target <= nums[0]) {
                return 0;
            } else {
                return 1;
            }
        }
        //保证左闭右开值域
        if (target < nums[0]) {
            return 0;
        }
        if (nums[len - 1] == target) {
            return len - 1;
        }
        if (nums[len - 1] < target) {
            return len;
        }

        int lo = 0, hi = len - 1;
        while (lo + 1 < hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (nums[mid] <= target) {
                lo = mid;
            } else{
                hi = mid;
            }
        }
        //程序执行到这里 必然是 lo+1=hi,并且nums[lo]<=target<nums[hi]
        if(nums[lo]==target){
            return lo;
        }else {
            return hi;
        }
    }

    /**
     * 左开右闭值域写法
     */
    public int searchInsert3(int[] nums, int target) {
        int len = nums.length;

        //对长度为1时的处理
        if (len == 1) {
            if (target <= nums[0]) {
                return 0;
            } else {
                return 1;
            }
        }
        //保证左开右闭值域
        if (target <= nums[0]) {
            return 0;
        }
        if (nums[len - 1] < target) {
            return len;
        }

        int lo = 0, hi = len - 1;
        while (lo + 1 < hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (target<=nums[mid]) {
                hi = mid;
            } else{
                lo = mid;
            }
        }
        //程序执行到这里 必然是 lo+1=hi,并且nums[lo]<target<=nums[hi]
       return hi;
    }

    public static void main(String[] args) {
        int[] nums={1,3,5,6};
        int target = 5;
        FindSearchPos demo = new FindSearchPos();

        System.out.println(demo.searchInsert1(nums,target));
        System.out.println(demo.searchInsert2(nums,target));
        System.out.println(demo.searchInsert3(nums,target));
        target=2;
        System.out.println(demo.searchInsert1(nums,target));
        System.out.println(demo.searchInsert2(nums,target));
        System.out.println(demo.searchInsert3(nums,target));
        target=7;
        System.out.println(demo.searchInsert1(nums,target));
        System.out.println(demo.searchInsert2(nums,target));
        System.out.println(demo.searchInsert3(nums,target));
    }
}
