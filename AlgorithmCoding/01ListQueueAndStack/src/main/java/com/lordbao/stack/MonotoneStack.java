package com.lordbao.stack;


import java.io.*;

/**
 * @Author Lord_Bao
 * @Date 2025/3/25 16:09
 * @Version 1.0
 *
 * 单调栈
 * 牛客链接 https://www.nowcoder.com/practice/2a2c00e7a88a498693568cef63a4b7bb
 *
 * 单调栈并不是一种新的数据结构，而是一种算法，即通过栈结构找到数组中每个元素左侧/右侧 离它最近
 * 且小于(或大于)它的位置
 *
 *
 * 下面是采用静态数组实现的栈,当然,也可以用JDK提供的栈
 */
public class MonotoneStack {


    private final static int MAX_N = 1000001;

    private static final int[] arr = new int[MAX_N];
    private static final int[] stack = new int[MAX_N];

    //记录左侧最近元素下标
    private static final int [] left = new int[MAX_N];
    //记录右侧最近元素下标
    private static final int [] right = new int[MAX_N];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(bf);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();

        //重置栈顶指针 top==-1 表示栈空
        int top=-1;

        //数组大小
        int n = (int)in.nval;
        for (int i = 0; i < n; i++) {
            in.nextToken();
            arr[i]=(int)in.nval;
        }

        //遍历阶段
        for (int i = 0; i < n; i++) {
            while (top!=-1 && arr[stack[top]]>= arr[i]){
                int index = stack[top];
                top--;
                left[index]= top==-1?-1:stack[top];
                right[index]=i;//此处可能需要修正
            }
            top++;
            stack[top]=i;
        }


        //清算阶段
        while (top!=-1){
            int index = stack[top];
            top--;
            left[index]= top==-1?-1:stack[top];
            right[index]=-1;
        }


        //修正阶段
        for (int i = n-2; i >=0 ; i--) {
            if(right[i]!=-1){
                if(arr[i]==arr[right[i]]){
                    right[i]=right[right[i]];
                }
            }
        }


        for (int i = 0; i < n; i++) {
            out.println(left[i]+" "+ right[i]);
        }


        out.flush();
        out.close();
        bf.close();
    }
}
