package com.lordbao.stack;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * @Author Lord_Bao
 * @Date 2026/5/10 13:27
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
 *
 * 另外一个解法 参见 {@link MonotoneStack}
 */
public class MonotoneStack2 {
    private static final int MAX_N = 10000001;
    private static int top = 0;//栈顶指针,初始为0
    private static final int[] stack = new int[MAX_N];

    private static int n;//表示arr的实际数组大小
    private static final int[] arr = new int[MAX_N];

    private static final int[] left = new int[MAX_N];
    private static final int[] right = new int[MAX_N];


    /***有关stack的操作***/
    //stack是否为空
    private static boolean isEmpty() {
        return top == 0;
    }

    private static void push(int val) {
        stack[top++] = val;
    }

    private static int pop() {
        return stack[--top];
    }

    private static int peek() {
        return stack[top - 1];
    }


    /**
     * 初始化: 处理输入内容 和 栈顶指针
     */
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        in.nextToken();
        int n = (int) in.nval;
        for (int i = 0; i < n; i++) {
            in.nextToken();
            arr[i] = (int) in.nval;
        }
        top = 0;//栈顶指针恢复0
        MonotoneStack2.n = n;//实际数组大小
    }

    /**
     * 第一操作 扫描数组
     */
    private static void proceedFirstStep() {
        for (int i = 0; i < n; i++) {
            //扫描元素比栈顶元素小时,才进行处理
            //这个阶段中，栈的元素从栈底到栈顶(存储的是下标)，
            //它们依次指向的元素必然是单增的(但不是严格单增，注意与解法1区分)。
            while (!isEmpty() && arr[peek()] > arr[i]) {
                int index = pop();
                right[index] = i;//right[index]必然是对的...
                //left[index]未必是对的,可能arr[index]==arr[left[index]],也就是不一定小于
                left[index] = isEmpty() ? -1 : peek();
            }
            push(i);
        }
    }

    /**
     * 第二个操作处理栈
     */
    private static void copeWithStackSecondStep() {
        while (!isEmpty()) {
            int index = pop();
            right[index] = -1;//right[index]必然是对的...
            //left[index]未必是对的,可能arr[index]==arr[left[index]],也就是不一定小于
            left[index] = isEmpty() ? -1 : peek();
        }
    }

    /**
     * 第三个操作修正,只需要修正left
     */
    private static void correctThirdStep() {
        //i从1开始,因为left[0]=-1必然是对的
        for (int i = 1; i < n; i++) {
            if (left[i] != -1) {
                if (arr[i] == arr[left[i]]) {//相等
                    left[i] = left[left[i]];
                }
            }
        }
    }


    /**
     * 输出答案
     */
    private static void output() {
        for (int i = 0; i < n; i++) {
            System.out.println(left[i] + " " + right[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        proceedFirstStep();
        copeWithStackSecondStep();
        correctThirdStep();
        output();
    }


}
