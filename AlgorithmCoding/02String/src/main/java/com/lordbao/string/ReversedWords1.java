package com.lordbao.string;


/**
 * @Author Lord_Bao
 * @Date 2024/10/19 11:28
 * @Version 1.0
 * 题目: 翻转字符串里的单词
 * 来源：https://leetcode.cn/leetbook/read/array-and-string/crmp5/
 * 已整理到笔记
 *
 */
class ReversedWords1 {

    //反向从坐标i处开始寻找，找到s下一个非空白符的下标
    int findNextNotWhiteSpace(String s,int i){
        while(i>=0){
            if(!Character.isWhitespace(s.charAt(i))){
                return i;
            }
            i--;
        }
        return -1;
    }

    //反向从坐标i处开始寻找，找到s下一个空白符的下标
    int findNextWhiteSpace(String s,int i){
        while(i>=0){
            if(Character.isWhitespace(s.charAt(i))){
                return i;
            }
            i--;
        }
        return -1;
    }



    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int p = s.length()-1;
        while (true){
            p = findNextNotWhiteSpace(s, p);
            if(p<0){
                break;
            }
            int q = findNextWhiteSpace(s, p);
            sb.append(s, q+1, p+1).append(" ");
            p=q;
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) {
//        String s = "a good   example";
//        System.out.println(new Solution().reverseWords(s));
        System.out.println(6>>1);
    }
}