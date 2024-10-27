package com.lordbao.array.leetcode.doubleArr;


import java.util.Arrays;

/**
 * @Author Lord_Bao
 * @Date 2024/10/19 17:33
 * @Version 1.0
 * 题目: 对角线遍历
 * 来源：https://leetcode.cn/leetbook/read/array-and-string/cuxq3/
 * 已整理到笔记
 */
public class DiagonalTraversal {



    public int[] findDiagonalOrder(int[][] mat) {
        int rowLen = mat.length;
        int colLen = mat[0].length;

        int[] arr = new int[ mat.length * mat[0].length];

        //0 表示右上, 1 表示左下
        int direction =0;
        int count = 0;
        arr[count]=mat[0][0];
        //起始坐标
        int i=0,j=0;
        while (true){
            if(direction==0){
                //右上可达
                while ((i-1)>=0 && (j+1)<colLen){
                    arr[++count]=mat[--i][++j];
                }
                //右优先，实在不行再下
                if(j+1<colLen){
                    arr[++count]=mat[i][++j];
                }else if(i+1<rowLen){
                    arr[++count]=mat[++i][j];
                }else {
                    break;
                }
                //变向
                direction=1;
            }else {
                //左下可达
                while ((i+1)<rowLen && (j-1)>=0){
                    arr[++count]=mat[++i][--j];
                }
                //下优先，实在不行再右
                if(i+1<rowLen){
                    arr[++count]=mat[++i][j];
                }else if(j+1<colLen){
                    arr[++count]=mat[i][++j];
                }else {
                    break;
                }
                //变向
                direction=0;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int [][]arr1={
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        int [][]arr2={
                {1,2,3},
                {4,5,6},
        };

        DiagonalTraversal demo = new DiagonalTraversal();
        int[] res1 = demo.findDiagonalOrder(arr1);
        System.out.println(Arrays.toString(res1));
        int[] res2 = demo.findDiagonalOrder(arr2);
        System.out.println(Arrays.toString(res2));
    }
}
