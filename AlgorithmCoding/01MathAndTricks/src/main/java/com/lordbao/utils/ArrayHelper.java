package com.lordbao.utils;


import java.util.Arrays;
import java.util.Random;

/**
 * @Author Lord_Bao
 * @Date 2026/5/3 16:10
 * @Version 1.0
 */
public class ArrayHelper {

    private static final Random RANDOM = new Random();

    /**
     *
     * @param lo 有序数组下限
     * @param hi 有序数组上限
     * @param n 有序数组数目
     * @return 返回范围在[lo, hi]的共 n个 互不相同 元素的有序数组
     * 当 lo > hi, n < 0,  n > hi-lo+1时,被视作非法情况
     *
     * 本函数在[lo,hi]区间很大，而n很小时,性能并不高. 在做测试时，必须要清楚这一点！
     * 原因在于本算法的思路是
     *  1 创建一个大小为 hi-lo+1的辅助数组arr,依次存储lo,lo+1,lo+2,....,hi
     *  2 对 arr 用Fisher-Yates 洗牌算法 进行随机洗牌
     *  3 取 arr的前n个元素构成子数组ans, 对ans排序后返回
     *
     *  显然,当[lo,hi]区间很大，而n很小时,性能并不高. 真遇到这种情况,可以考虑下面这种思路
     *    while(set.size()<n){
     *        set.add(RANDOM.nextInt(lo,hi+1));//set添加[lo,hi]之间元素
     *    }
     *
     */
    public static int[] randomDistinctOrderArray(int lo, int hi, int n) {
        //参数校验
        if (n < 0) {
            throw new IllegalArgumentException("数组数目不能为负数 " + n);
        } else if (lo > hi) {
            throw new IllegalArgumentException("有序数组下限 " + lo + " 不能大于上限" + hi);
        } else if (n > hi - lo + 1) {
            throw new IllegalArgumentException("数组数目 " + n + " 不能比 " + (hi - lo + 1) + " 大,因为根据鸽槽原理,必有重复元素");
        }

        //1.创建辅助数组
        //arr[0]=lo,arr[1]=lo+1,arr[2]=lo+2,...,arr[hi-lo]=hi
        int[] arr = new int[hi - lo + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = lo + i;
        }


        //2.Fisher-Yates 洗牌算法
        //arr[i] 只能和 前面arr[0...i]的元素换,以保证充分洗牌
        //arr[i] 不能是于arr[0...arr.length-1]的元素换,这样换并不均分
        //记住就行了,不深究
        for (int i = arr.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);//j范围在0到i之间
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        //3.复制arr的前n个元素形成数组ans,再对ans进行排序.
        //这样的ans的所有元素在[lo,hi]之间,且不重复
        int[] ans = Arrays.copyOf(arr, n);
        Arrays.sort(ans);
        return ans;
    }

    /**
     *
     * @param lo 有序数组下限
     * @param hi 有序数组上限
     * @param n 有序数组数目
     * @return 返回范围在[lo, hi]的共 n个 互不相同 元素的有序数组
     * 当 lo > hi, n < 0时,被视作非法情况
     *
     */
    public static int[] randomOrderArray(int lo, int hi, int n) {
        //参数校验
        if (n < 0) {
            throw new IllegalArgumentException("数组数目不能为负数 " + n);
        } else if (lo > hi) {
            throw new IllegalArgumentException("有序数组下限 " + lo + " 不能大于上限" + hi);
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = RANDOM.nextInt(hi - lo + 1) + lo;//[lo...hi]之间的随机数
        }
        Arrays.sort(arr);
        return arr;
    }

}
