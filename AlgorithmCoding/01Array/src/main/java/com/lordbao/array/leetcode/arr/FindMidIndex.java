package com.lordbao.array.leetcode.arr;


/**
 * @Author Lord_Bao
 * @Date 2024/10/23 10:27
 * @Version 1.0
 *
 * 题目: 寻找数组的中心索引
 * 来源：https://leetcode.cn/leetbook/read/array-and-string/yf47s/
 * 已整理到笔记
 */
public class FindMidIndex {
    public int pivotIndex(int[] nums) {
        int total =0;
        int len = nums.length;
        //先求得总和
        for(int i=0;i<len;i++){
            total+=nums[i];
        }

        //左侧和
        int leftSum=0;
        for(int i=0;i<len;i++){
            //右侧和
            int rightSum = total - leftSum - nums[i];
            if(leftSum==rightSum){
                return i;
            }
            leftSum+=nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(-5/2);
    }
}
