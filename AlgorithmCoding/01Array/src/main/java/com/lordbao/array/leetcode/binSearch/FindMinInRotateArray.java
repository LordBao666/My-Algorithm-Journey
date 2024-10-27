package com.lordbao.array.leetcode.binSearch;


/**
 * @Author Lord_Bao
 * @Date 2024/10/24 15:14
 * @Version 1.0
 *
 *
 * 题目: 寻找旋转排序数组中的最小值
 * 来源：https://leetcode.cn/leetbook/read/array-and-string/c3ki5/
 * 已整理到笔记
 */

public class FindMinInRotateArray {
    //nums.length >=1
    public int findMin(int[] nums) {
        if(nums[0]<nums[nums.length-1]){
            return nums[0];
        }
        int lo=0,hi=nums.length-1;
        while (lo+1<hi){
            int mid=lo +((hi-lo)>>1);
            if(nums[lo]<nums[mid]){
                lo=mid;
            }else if(nums[mid]<nums[hi]){
                hi=mid;
            }
        }
        return nums[hi];
    }



    public static void main(String[] args) {
        FindMinInRotateArray demo = new FindMinInRotateArray();
        int [] nums={3,4,5,1,2};
        System.out.println(demo.findMin(nums));
        nums=new int[]{4,5,6,7,0,1,2};
        System.out.println(demo.findMin(nums));
        nums=new int[]{11,13,15,17};
        System.out.println(demo.findMin(nums));

    }
}
