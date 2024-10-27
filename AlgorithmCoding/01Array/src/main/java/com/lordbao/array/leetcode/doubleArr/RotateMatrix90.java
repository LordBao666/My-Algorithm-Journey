package com.lordbao.array.leetcode.doubleArr;


import java.util.Arrays;

/**
 * @Author Lord_Bao
 * @Date 2024/10/19 16:22
 * @Version 1.0
 * 题目: 合并区间
 * 来源：https://leetcode.cn/leetbook/read/array-and-string/clpgd/
 * 已整理到笔记
 *
 */
public class RotateMatrix90 {
    public void rotate(int[][] matrix) {
        int N = matrix.length;
        //外层循环次数为N/2
        for(int i=0;i<N/2;i++){
            for(int j=i;j<N-i-1;j++){
                int t = matrix[i][j];
                matrix[i][j]=matrix[N-j-1][i];
                matrix[N-j-1][i]=matrix[N-i-1][N-j-1];
                matrix[N-i-1][N-j-1]=matrix[j][N-i-1];
                matrix[j][N-i-1]=t;
            }
        }
    }

    public static void main(String[] args) {
        int [][] arr = {
                {5,1,9,11},
                {2,4,8,10},
                {13,3,6,7},
                {15,14,12,16},
        };
        new RotateMatrix90().rotate(arr);
        System.out.println(Arrays.deepToString(arr));
    }
}
