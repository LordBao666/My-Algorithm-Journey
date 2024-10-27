package com.lordbao.array.leetcode.binSearch;


/**
 * @Author Lord_Bao
 * @Date 2024/10/21 16:47
 * @Version 1.0
 * 题目: 搜索旋转数组
 * 来源：https://leetcode.cn/leetbook/read/binary-search/xeog5j/
 * 已整理到笔记
 */
public class SearchRotationSortingArray {

    public int search(int[] nums, int target) {
        if(nums==null || nums.length==0){
            return -1;
        }
        if(nums.length==1){
            return target==nums[0]?0:-1;
        }
        //针对的是 [1,2,3,4,5]这种数组
        if(nums[0]<nums[nums.length-1]){
            return binarySearch(nums,0,nums.length-1,target);
        }

        int lo = 0,hi=nums.length-1;
        while (lo+1<hi){
            int mid = lo + ((hi-lo)>>1);
            //mid属于左半区
            if(nums[lo]<nums[mid]){
                lo=mid;
            //mid属于右半区
            }else if(nums[mid]<nums[hi]){
                hi=mid;
            }
        }

        int res1=binarySearch(nums,0,lo,target);
        if(res1!=-1){
            return res1;
        }
        return binarySearch(nums,lo+1,nums.length-1,target);
    }


    private int binarySearch(int [] nums,int start,int stop,int target){

        int lo = start;
        int hi = stop;
        while (lo<=hi){
            int mid = lo + ((hi-lo)>>1);
            if(nums[mid]<target){
                lo=mid+1;
            }else if(target<nums[mid]){
                hi=mid-1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int []nums = {4,5,6,7,0,1,2};
        int target = 0;
        SearchRotationSortingArray demo = new SearchRotationSortingArray();

        System.out.println(demo.search(nums,target));


        target=3;
        System.out.println(demo.search(nums,target));

        nums = new int[]{1};
        target = 0;
        System.out.println(demo.search(nums,target));

        nums = new int[]{1,2,3,4,5};
        target = 3;
        System.out.println(demo.search(nums,target));
    }
}
