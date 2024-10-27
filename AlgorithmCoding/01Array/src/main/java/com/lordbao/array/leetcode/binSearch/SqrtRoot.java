package com.lordbao.array.leetcode.binSearch;


/**
 * @Author Lord_Bao
 * @Date 2024/10/21 16:47
 * @Version 1.0
 * 题目: x的平方根
 * 来源：https://leetcode.cn/leetbook/read/binary-search/xe9cog/
 * 已整理到笔记
 */
public class SqrtRoot {


    public int mySqrt(int x) {
        if(x==0){return 0;}

        int lo = 1, hi=x;
        while(lo<=hi){
            int mid = lo + ((hi-lo)>>1);
            //mid估计过小
            if((mid+1)<=x/(mid+1)){
                lo=mid+1;
                //mid估计过大
            }else if(mid > x/mid){
                hi=mid-1;
            }else{//mid <= x/mid && x/(mid+1) <(mid+1)
                return mid;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        SqrtRoot demo = new SqrtRoot();
        for(int i=0;i<100;i++){
            System.out.println(i+":"+demo.mySqrt(i)+","+demo.mySqrt(i));
        }
    }
}