package com.lordbao.array.leetcode.arr;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/10/23 10:27
 * @Version 1.0
 *
 * 题目: 杨辉三角2
 * 来源：https://leetcode.cn/leetbook/read/array-and-string/ctyt1/
 * 已整理到笔记
 */
public class YangHuiTriangle {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        if(rowIndex==0){
            list.add(1);
            return list;
        }
        if(rowIndex==1){
            list.add(1);
            list.add(1);
            return list;
        }
        int [] arr = new int[rowIndex];
        arr[0]=1;
        arr[1]=1;
        //i  表示rowindex
        for(int i=2;i<rowIndex;i++){
            //t 用于上一次计算结果，这里特地初始化为1
            int t=1;
            //j表示从当前row的第1个元素(坐标从0开始)
            for(int j=1;j<i;j++){
                int result = arr[j-1]+arr[j];
                arr[j-1]=t;
                t=result;
            }
            arr[i-1]=t;
            arr[i]=1;
        }

        list.add(1);
        for(int i=1;i<rowIndex;i++){
            list.add(arr[i-1]+arr[i]);
        }
        list.add(1);
        return list;
    }

    public static void main(String[] args) {
        YangHuiTriangle demo = new YangHuiTriangle();
        List<Integer> list = demo.getRow(4);
        System.out.println(list);
    }
}