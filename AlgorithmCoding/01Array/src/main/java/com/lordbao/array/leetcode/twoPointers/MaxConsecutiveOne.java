package com.lordbao.array.leetcode.twoPointers;


/**
 * @Author Lord_Bao
 * @Date 2024/10/23 17:55
 * @Version 1.0
 * 题目: 最大连续1的个数
 * 来源：https://leetcode.cn/leetbook/read/array-and-string/cd71t/
 * 已整理到笔记
 */
public class MaxConsecutiveOne {

    public int findMaxConsecutiveOnes(int[] nums) {
        int i=-1,j=0;
        //最大连续1的个数
        int count = 0;
        while (j<nums.length){
            if(nums[j]==1){
                j++;
            }else {
                count=Math.max(j-i-1,count);
                i=j;
                j++;
            }
        }
        return Math.max(count,j-i-1);
    }

    public static void main(String[] args) {
        MaxConsecutiveOne demo = new MaxConsecutiveOne();
        int [] nums = {1,1,0,1,1,1};
        System.out.println(demo.findMaxConsecutiveOnes(nums));


        nums = new int[]{0,1,1,1,0};
        System.out.println(demo.findMaxConsecutiveOnes(nums));


    }
}
