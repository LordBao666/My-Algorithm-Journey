package com.lordbao.array.leetcode.arr;


import java.util.Arrays;

/**
 * @Author Lord_Bao
 * @Date 2024/10/23 12:45
 * @Version 1.0
 * 题目: 数组拆分
 * 来源：https://leetcode.cn/leetbook/read/array-and-string/c24he/
 * 已整理到笔记
 */
public class ArraySplit {


    //数组长度为2n，且n>=1
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res=0;
        for(int i=0;i<nums.length;i=i+2){
            res+=nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
       int [] nums = {6,2,6,5,1,2};
        ArraySplit demo = new ArraySplit();
        System.out.println(demo.arrayPairSum(nums));
    }
}
