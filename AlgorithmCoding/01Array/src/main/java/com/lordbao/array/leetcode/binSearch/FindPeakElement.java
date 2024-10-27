package com.lordbao.array.leetcode.binSearch;


/**
 * @Author Lord_Bao
 * @Date 2024/10/21 17:30
 * @Version 1.0
 * 题目: 寻找峰值
 * 来源：https://leetcode.cn/leetbook/read/binary-search/xem7js/
 * 已整理到笔记
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[len - 2] < nums[len - 1]) {
            return len - 1;
        }
        int lo = 0, hi = len - 1;


        while (lo + 1 < hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (nums[mid] < nums[mid + 1]) {
                lo = mid;
            } else if (nums[mid - 1] > nums[mid]) {
                hi = mid;
            } else if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) {
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        FindPeakElement demo = new FindPeakElement();
        System.out.println(demo.findPeakElement(nums));

        nums = new int[]{1, 2, 3, 1};
        System.out.println(demo.findPeakElement(nums));

        nums = new int[]{1, 2, 3, 4};
        System.out.println(demo.findPeakElement(nums));
        nums = new int[]{4, 3, 2, 1};
        System.out.println(demo.findPeakElement(nums));
    }

}
