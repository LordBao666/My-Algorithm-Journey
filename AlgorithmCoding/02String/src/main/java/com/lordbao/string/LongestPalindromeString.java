package com.lordbao.string;


/**
 * @Author Lord_Bao
 * @Date 2024/10/23 15:48
 * @Version 1.0
 * 题目: 最长回文子串
 * 来源：https://leetcode.cn/leetbook/read/array-and-string/conm7/
 * 已整理到笔记
 */
public class LongestPalindromeString {
    /**
     * 判断字符串 起始下标i(含)  到 终止下标(j) 的 子串是否为回文
     */
    private boolean isPalindrome(String s, int i, int j) {
        if (s == null || s.length() == 0) {
            return true;
        }


        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    /***
     * 暴力解法，时间复杂度为 O(n^3)
     */
    public String longestPalindrome1(String s) {
        int len = s.length();
        //i表示扫描长度
        for (int i = len; i >= 1; i--) {
            //j表示扫描起始位置
            for (int j = 0; j <= len - i; j++) {
                if (isPalindrome(s, j, j + i - 1)) {
                    return s.substring(j, j + i);
                }
            }
        }
        return "";
    }

    /***
     * `arr[i][j]`的值只能有2个：-1 和1，
     * `a[i][j]`表示以下标i为回文中心，扩展长度为j的字符串是否为回文，若值为-1，表示不是回文，若值为1，表示是回文
     * 解释一下`a[i][j]`表示以下标i为回文中心，扩展长度为j的字符串的含义，
     *
     * 1. `a[2][0]=1`表示下标从2开始的字符为回文；
     * 2. `a[2][1]=1`表示下标从2开始，往右1长度的字符串为回文；
     * 3. `a[2][2]=1`表示下标从2开始，往右1长度，往左1长度(共2长度)的字符串为回文；
     * 4. `a[2][3]=1`表示下标从2开始，往右2长度，往左1长度(共3长度)的字符串为回文；
     * 5. `a[2][4]=1`表示下标从2开始，往右2长度，往左2长度(共4长度)的字符串为回文。
     *
     * 题目假设s的长度至少为1
     */
    public String longestPalindrome2(String s) {
        if (s.length() == 1) {
            return s;
        }
        int len = s.length();
        //默认为0
        int[][] arr = new int[len][len];

        for (int i = 0; i < len; i++) {
            arr[i][0] = 1;
        }
        //处理长度为2的子串
        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                arr[i][1] = 1;
            } else {
                arr[i][1] = 0;//实际上这步不用做，因为默认为0
            }
        }

        //填表
        for (int j = 2; j < len; j++) {
            for (int i = 0; i < len; i++) {
                if (arr[i][j - 2] == 0) {
                    arr[i][j] = 0;
                } else {
                    int leftCharIndex = i - j / 2;
                    int rightCharIndex = j % 2 == 0 ? i + j / 2 : i + j / 2 + 1;
                    //字符越界
                    if (leftCharIndex < 0 || rightCharIndex >= len) {
                        arr[i][j] = 0;
                    } else {
                        char c1 = s.charAt(leftCharIndex);
                        char c2 = s.charAt(rightCharIndex);
                        if (c1 == c2) {
                            arr[i][j] = 1;
                        } else {
                            arr[i][j] = 0;
                        }
                    }
                }
            }
        }

        int j = len - 1, i = 0;
        for (; j >= 0; j--) {
            i = 0;
            for (; i < len; i++) {
                if (arr[i][j] == 1) {
                    int leftIndex = i - j / 2;
                    int rightIndex = j % 2 == 0 ? i + j / 2 : i + j / 2 + 1;
                    return s.substring(leftIndex, rightIndex + 1);
                }
            }
        }

        return "";
    }


    /**
     * LeetCode的动态规划思想, arr[i][j]的含义下标i到j的子串是否是回文
     * 0表示不是回文，1表示是回文
     * <p>
     * 字符串s的长度至少为1
     */
    public String longestPalindrome2A(String s) {
        if (s.length() == 1) {
            return s;
        }
        int len = s.length();
        //初始情况下 arr[][] 均为0
        int[][] arr = new int[len][len];
        for (int i = 0; i < len; i++) {
            //处理长度为1的单个字符
            arr[i][i] = 1;
        }
        //处理长度为2的字符串
        for (int i = 0; i < len - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                arr[i][i + 1] = 1;
            }
            //如果不等，则无需处理，因为arr默认都为0
        }
        //外层循环的i表示处理的字符串长度，此处直接从3开始
        for (int i = 3; i <= len; i++) {
            //内层循环的j表示字符串的起始下标
            for (int j = 0; j <= len - i; j++) {
                if (arr[j + 1][j + i - 2] == 1) {
                    if (s.charAt(j) == s.charAt(j + i - 1)) {
                        arr[j][j + i - 1] = 1;
                    }
                }
                //不是回文的情况无需处理，因为arr默认都为0
            }
        }

        //外层循环的j表示 子串长度
        for (int j = len; j >= 1; j--) {
            //里面的i表示起始坐标
            for (int i = 0; i <= len - j; i++) {
                if (arr[i][i + j - 1] == 1) {
                    return s.substring(i, i + j);
                }
            }
        }
        return "";
    }

    /**
     * 中心扩展法 时间复杂度为 O(n^2) 空间复杂度为O(1)
     * 字符串的长度至少为1
     */
    public String longestPalindrome3(String s) {
        if (s.length() == 1) {
            return s;
        }

        //记录回文的最大长度,起始下标和终止下标
        int maxLen = 0;
        int start=0;
        int end=0;
        for(int i=0;i<s.length();i++){
            int len1 = lengthAroundCenter(s, i, i);
            int len2 = lengthAroundCenter(s, i, i + 1);
            if(maxLen<len1 || maxLen<len2){
                maxLen=Math.max(len1,len2);
                //单中心回文的长度(只能是奇数)更长
                if(len1>len2){
                    start= i - len1/2;
                    end = i+ len1/2;
                }else { //双中心回文的长度(只能是偶数)更长
                    start =i-len2/2+1;
                    end = i + len2/2;
                }
            }
        }
        return s.substring(start,end+1);
    }

    /**
     * 返回以left 和 right 为 中心的最长回文长度
     * left 可以 等于 right
     */
    public int lengthAroundCenter(String s , int left,int right){
        while (0<=left && right<s.length()){
            if(s.charAt(left) == s.charAt(right)){
                left--;
                right++;
            }else {
                break;
            }
        }
        return right-left-1;

    }


    public static void main(String[] args) {
        LongestPalindromeString demo = new LongestPalindromeString();
        System.out.println(demo.longestPalindrome2("babad"));
        System.out.println(demo.longestPalindrome2("cbbd"));
        System.out.println(demo.longestPalindrome2("acdtfeaeftdcak"));
        System.out.println("----------");
        System.out.println(demo.longestPalindrome2A("babad"));
        System.out.println(demo.longestPalindrome2A("cbbd"));
        System.out.println(demo.longestPalindrome2A("acdtfeaeftdcak"));
        System.out.println("----------");
        System.out.println(demo.longestPalindrome3("babad"));
        System.out.println(demo.longestPalindrome3("cbbd"));
        System.out.println(demo.longestPalindrome3("acdtfeaeftdcak"));
    }
}
