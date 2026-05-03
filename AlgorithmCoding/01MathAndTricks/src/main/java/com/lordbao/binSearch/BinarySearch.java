package com.lordbao.binSearch;


/**
 * @Author Lord_Bao
 * @Date 2024/12/10 10:04
 * @Version 1.0
 *
 * 二分查找的代码模板
 * 测试案例 请见 test 文件夹
 */
public class BinarySearch {

    /**/

    /**
     *
     * @param nums 非空有序数组
     * @param target 目标值
     * @return 返回目标值在有序数组中的下标, 如果不存在, 则返回-1
     */
    public static int binSearch0(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        int mi;
        while (lo <= hi) {
            mi = lo + ((hi - lo) >> 1);//>>的优先级低于+, 所以这里要打括号
            if (nums[mi] < target) {
                lo = mi + 1;
            } else if (target < nums[mi]) {
                hi = mi - 1;
            } else {
                return mi;
            }
        }
        return -1;
    }


    /**
     *
     * @param nums 非空有序数组
     * @param target 目标值
     * @return 返回有序数组中nums[i] <= target的最大i
     * 以数组int [] nums={1,1,1,3,3,3,7}为例，
     * target为1，那么i就是2；
     * target为3，那么i就是5；
     * target为-1，那么i就是-1，此时表示数组中不存在小于等于target的元素；
     * target为8，那么i就是6。
     */
    public static int binSearch1(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        int mi;
        int ans = -1;
        while (lo <= hi) {
            mi = lo + ((hi - lo) >> 1);
            if (nums[mi] <= target) {
                ans = mi;
                lo = mi + 1;
            } else {
                hi = mi - 1;
            }
        }
        return ans;
    }


    /**
     *
     * @param nums 非空有序数组
     * @param target 目标值
     * @return 返回有序数组中   nums[i] < target的最大i
     * 以数组int [] nums={1,1,1,3,3,3,7}为例，
     * target为1，那么i就是-1,表示数组中不存在元素小于target.
     * target为3，那么i就是2；
     * target为7，那么i就是5
     * target为8，那么i就是6.
     */
    public static int binSearch2(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        int mi;
        int ans = -1;
        while (lo <= hi) {
            mi = lo + ((hi - lo) >> 1);
            if (nums[mi] < target) {
                ans = mi;
                lo = mi + 1;
            } else {
                hi = mi - 1;
            }
        }
        return ans;
    }
}
