package com.lordbao.array.leetcode.arr;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Lord_Bao
 * @Date 2024/10/22 17:21
 * @Version 1.0
 * 题目: 合并区间
 * 来源：https://leetcode.cn/leetbook/read/array-and-string/c5tv3/
 * 已整理到笔记
 */
public class CombineIntervals {
//    private void quickSort(int [][] intervals){
//        if(intervals==null || intervals.length==0 || intervals.length==1){
//            return;
//        }
//
//        quickSort(0,intervals.length-1,intervals);
//    }
//
//    private int findPivot(int lo,int hi,int[][] intervals){
//        int p1 = intervals[lo][0];
//        int p2 = intervals[lo][1];
//        while (lo<hi){
//            while (p1<=intervals[hi][0] && lo<hi){
//                hi--;
//            }
//            if(p1>intervals[hi][0]){
//                intervals[lo][0]=intervals[hi][0];
//                intervals[lo][1]=intervals[hi][1];
//            }else {//lo==hi
//                break;
//            }
//            while (intervals[lo][0] <= p1&& lo<hi){
//                lo++;
//            }
//            if(p1<intervals[lo][0]){
//                intervals[hi][0]=intervals[lo][0];
//                intervals[hi][1]=intervals[lo][1];
//            }else {//lo==hi
//                break;
//            }
//        }
//
//        intervals[lo][0]=p1;
//        intervals[lo][1]=p2;
//        return lo;
//    }
//
//    private void quickSort(int lo,int hi, int [][] intervals){
//        if(lo<hi){
//            int pivot = findPivot(lo,hi,intervals);
//            quickSort(lo,pivot-1,intervals);
//            quickSort(pivot+1,hi,intervals);
//        }
//    }
    public int[][] merge(int[][] intervals) {
        if(intervals==null|| intervals.length==0 || intervals.length==1){
            return intervals;
        }
//        quickSort(intervals);
        Arrays.sort(intervals, (o1, o2) -> o1[0]-o2[0]);
        int i = 1;
        int left = intervals[0][0];
        int right = intervals[0][1];

        ArrayList<Integer[]> list = new ArrayList<>();
        while (i<intervals.length){
            if(right<intervals[i][0]){
                list.add(new Integer[]{left,right});
                left=intervals[i][0];
                right=intervals[i][1];
            }else {
                right = Math.max(right,intervals[i][1]);
            }
            i++;
        }
        list.add(new Integer[]{left,right});

        int[][] arr= new int[list.size()][2];
        for(int j=0;j<list.size();j++){
            arr[j][0]=list.get(j)[0];
            arr[j][1]=list.get(j)[1];
        }
        return arr;
    }

    public static void main(String[] args) {
        int [][] intervals = {
                {1,3},{8,10},{2,6},{15,18}
        };
        CombineIntervals demo = new CombineIntervals();
//        demo.quickSort(intervals);
//        System.out.println(Arrays.deepToString(intervals));
        int[][] arr = demo.merge(intervals);
        System.out.println(Arrays.deepToString(arr));


    }
}
