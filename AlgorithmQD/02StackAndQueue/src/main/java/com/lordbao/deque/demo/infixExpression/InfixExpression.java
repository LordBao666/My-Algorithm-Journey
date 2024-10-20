package com.lordbao.deque.demo.infixExpression;


import com.lordbao.deque.impl.MyLinkedListDeque;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @Author Lord_Bao
 * @Date 2024/10/18 14:16
 * @Version 1.0
 */
@Slf4j
public class InfixExpression {

    /**
     * 优先级数组
     * 列表示栈顶操作符，行表示刚扫描的操作符。依次为+ - * / ( ) #
     * 1  表示栈顶操作符优先级 > 刚扫描的操作符
     * -1 表示栈顶操作符优先级 < 刚扫描的操作符
     * 0 表示非法。注意，它不表示优先级相等。
     */
    private static final int[][] priority = {
            {1, 1, -1, -1, -1, 1, 1},
            {1, 1, -1, -1, -1, 1, 1},
            {1, 1, 1, 1, -1, 1, 1},
            {1, 1, 1, 1, -1, 1, 1},
            {-1, -1, -1, -1, -1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {-1, -1, -1, -1, -1, 0, 1}
    };

    /**
     * 用户输入的合法的操作符
     */
    private static final char[] legalChars = {
            '+', '-', '*', '/', '(', ')'
    };


    /**
     * 返回 操作符 的下标
     */
    private static int getLegalCharIndex(char operator) {
        int length = legalChars.length;
        int i = 0;
        for (; i < length; i++) {
            if (operator == legalChars[i]) {
                return i;
            }
        }
        if (operator == '#') {
            return i;
        }
        return -1;
    }

    /**
     * 从String的下标为i的地方开始找，返回第一个非 数字字符的下标。
     * 如果不存在，则返回-1
     */
    private static int nextNotDigit(String s, int i) {
        while (i < s.length()) {
            if (!Character.isDigit(s.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * 进行 + - * / 计算的帮助方法。如果是非法操作符，则报错
     */
    private static Integer doCountHelper(char operator, Integer leftOperand, Integer rightOperand) {
        switch (operator) {
            case '+':
                return leftOperand + rightOperand;
            case '-':
                return leftOperand - rightOperand;
            case '*':
                return leftOperand * rightOperand;
            case '/':
                return leftOperand / rightOperand;
            default: {
                String error = leftOperand + " " + operator + " " + rightOperand + " 是非法计算";
                log.error(error);
                throw new IllegalArgumentException(error);
            }
        }
    }

    /***
     * @param input 处理过后的用户输入的字符串
     * @param operator 扫描到的操作符
     * @param operatorStack 操作符栈
     * @param operandStack 操作数栈
     *
     * 根据扫描到的操作符进行处理
     */
    private static void manipulateOperator(String  input, char operator, MyLinkedListDeque<Character> operatorStack,
                                           MyLinkedListDeque<Integer> operandStack) {

        int afterIndex = getLegalCharIndex(operator);

        while (!operatorStack.isEmpty()) {
            int preIndex = getLegalCharIndex(operatorStack.getLast());
            int prior = priority[preIndex][afterIndex];

            //栈顶操作符优先级更低时，operator 直接入符号栈
            if (prior < 0) {
                operatorStack.addLast(operator);
                return;
            } else if (prior > 0) {
                //栈顶操作符优先级更高时。注意我们设计 ) 永远不会入栈，那么栈顶操作符只能是 + - * /  和 (,#
                if(operatorStack.getLast()=='('){
                    operatorStack.removeLast();
                    break;
                }else if (operatorStack.getLast()=='#' && operator=='#'){ //  # 运算终止
                    operatorStack.removeLast();
                    return;
                }else {//    + - * /
                    Integer rightOperand = operandStack.removeLast();
                    Integer leftOperand = operandStack.removeLast();
                    Integer result = doCountHelper(operatorStack.removeLast(), leftOperand, rightOperand);
                    operandStack.addLast(result);
                }
            } else {
                String error = input + "非法";
                log.error(error);
                throw new IllegalArgumentException(error);
            }
        }

        //代码若执行到此处,
        //由于处理了operator的这三种情况 1.优先级表为 < 的直接入栈，并return; 2.以及 - 的 报错异常 3. # 直接return;
        //所以这里的operator 只能是 + - * / )。我们保证+ - * / 入符号栈，而) 不入栈。
        if(operator!=')'){
            operatorStack.addLast(operator);
        }

    }

    public static int infixExpression(String input) {
        //操作符栈
        MyLinkedListDeque<Character> operatorStack = new MyLinkedListDeque<>();
        operatorStack.addLast('#');

        //操作数栈
        MyLinkedListDeque<Integer> operandStack = new MyLinkedListDeque<>();

        int i = 1;
        while (i < input.length()) {
            char c = input.charAt(i);
            //如果是数字
            if (Character.isDigit(c)) {
                //从i+1处开始寻找，找到首个非数字的下标
                int index = nextNotDigit(input, i + 1);
                operandStack.addLast(Integer.parseInt(input.substring(i, index)));
                i = index;
            } else { //必然是规定的操作符
                manipulateOperator(input,c,operatorStack,operandStack);
                i++;
            }
        }
        Integer result = operandStack.removeLast();
        //非法情况
        if(!operatorStack.isEmpty() || !operatorStack.isEmpty()){
            String error = input + "非法";
            log.error(error);
            throw new IllegalArgumentException(error);
        }
        return result;
    }

    public static void main(String[] args) {
        //得到用户输入，预处理输入，添加终止符
        String input = getUserInput();
        input = checkInput(input);
        input = '#' + input + '#';
        System.out.println(input);
        //得到结果
        int result = infixExpression(input);
        System.out.println(result);
    }

    /**
     * 检验字符 是否为 合法的操作符
     */
    private static boolean isOperator(char c) {
        for (char ele : legalChars) {
            if (ele == c) {
                return true;
            }
        }
        return false;
    }

    /***
     *
     * @param input 用户输入字符串
     * @return 去除掉空白符的字符串。如果含非法字符，则直接抛出异常
     */
    private static String checkInput(String input) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            char c = input.charAt(i);
            //如果是空白符,直接忽略
            if (Character.isWhitespace(input.charAt(i))) {
                i++;
                //合法的数字 或 操作符
            } else if (Character.isDigit(c) || isOperator(c)) {
                sb.append(c);
                i++;
                //非法字符，直接抛出异常
            } else {
                String error = input + "存在非法字符" + c;
                log.error(error);
                throw new IllegalArgumentException(error);
            }
        }
        return sb.toString();
    }

    private static String getUserInput() {
        System.out.print("Enter your expression: ");
        String s = "";
        try (Scanner sc = new Scanner(System.in)) {
            s = sc.nextLine();
        }
        return s;
    }


}
