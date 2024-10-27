package com.lordbao.array.leetcode.twoPointers;


/**
 * @Author Lord_Bao
 * @Date 2024/10/23 19:55
 * @Version 1.0
 * 题目: 长度最小的子数组
 * 来源：https://leetcode.cn/leetbook/read/array-and-string/c0w4r/
 * 已整理到笔记
 *
 */
public class MinSubArrayLen {
    //注意 nums的长度至少为1，并且均为正整数
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        //最小长度的子数组的长度
        int minLen=Integer.MAX_VALUE;

        //i表示区间左端点,j表示区间右端点，始终保存i<=j
        int i=0,j=0;
        //表示区间总和
        int total = nums[0];

        while (true){
            if(target<total){
                minLen = Math.min(minLen,j-i+1);
                //此时区间长度为1，直接退出，无需再判断
                if(i==j){
                    break;
                }
                total-=nums[i];
                //代码执行到此处 必然有 i<j<nums.length，因此i++ 不可能越界
                i++;//
            }else if(total<target){
                j++;
                //扫描完毕
                if(j==len){
                    break;
                }
                total+=nums[j];
            }else {
                minLen = Math.min(minLen,j-i+1);
                if(i==j){
                    break;
                }
                j++;
                //扫描完毕
                if(j==len){
                    break;
                }
                total=total-nums[i]+nums[j];
                //代码执行到此处 必然有 i<j<nums.length，因此i++ 不可能越界
                i++;
            }
        }

        return minLen==Integer.MAX_VALUE?0:minLen;
    }

    public static void main(String[] args) {
        int [] nums = {2,3,1,2,4,3};
        MinSubArrayLen demo = new MinSubArrayLen();
        System.out.println(demo.minSubArrayLen(7,nums));

        nums= new int[]{1,4,4};
        System.out.println(demo.minSubArrayLen(4,nums));

        nums= new int[]{1,1,1,1,1,1,1,1};
        System.out.println(demo.minSubArrayLen(11,nums));
    }
}
