package com.lordbao.stack;


/**
 * @Author Lord_Bao
 * @Date 2025/3/25 10:46
 * @Version 1.0
 * <p>
 * MinStack 是在常规Stack的基础上 额外提供一个操作getMin,以返回栈的最小值
 */
public class MinStack {
    private final MyNode head;

    public MinStack() {
        head = new MyNode(null, -1, -1);
    }

    public void push(int val) {
        head.next = head.next == null ?
                new MyNode(null, val, val) :
                new MyNode(head.next, val, Math.min(val, head.next.min));
    }

    //注意题目要求不会在栈空时实现pop操作,因此下列的head.next 不可能为空
    public void pop() {
        head.next = head.next.next;//head.next不可能为空
    }

    public int top() {
        return head.next.val;
    }

    public int getMin() {
        return head.next.min;
    }


    private static class MyNode {
        private MyNode next;
        private int val;
        private int min;

        public MyNode(MyNode next, int val, int min) {
            this.next = next;
            this.val = val;
            this.min = min;
        }
    }
}
