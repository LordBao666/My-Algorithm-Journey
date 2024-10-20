package com.lordbao.deque.demo.infixExpression;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Lord_Bao
 * @Date 2024/10/18 15:21
 * @Version 1.0
 *
 * 下面的测试需要将对应的方法改为public才奏效
 */
class InfixExpressionTest {


//    @Test
//    public void checkInput(){
//        String s = "1234567";
//        Assertions.assertEquals(s,InfixExpression.checkInput(s));
//
//        s="12    4567";
//        Assertions.assertEquals("124567",InfixExpression.checkInput(s));
//
//
//        s="12+3-9/6 + (3+6)*4/2";
//        Assertions.assertEquals("12+3-9/6+(3+6)*4/2",InfixExpression.checkInput(s));
//
//        s="12+3-9/6 + (3+6)*4/2@";
//        String finalS = s;
//        Assertions.assertThrows(IllegalArgumentException.class,()->{String temp = InfixExpression.checkInput(finalS);});
//    }

//    @Test
//    public void nextNotDigit(){
//
//        String s = "123#555*333";
//        Assertions.assertEquals(3,InfixExpression.nextNotDigit(s,0));
//        Assertions.assertEquals(7,InfixExpression.nextNotDigit(s,4));
//        Assertions.assertEquals(-1,InfixExpression.nextNotDigit(s,8));
//    }

    @Test
    public void testIsDigit(){
        System.out.println(Character.isDigit('('));
    }
}