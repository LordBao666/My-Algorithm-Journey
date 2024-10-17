package com.lordbao.deque.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Lord_Bao
 * @Date 2024/10/17 17:50
 * @Version 1.0
 */
class MyArrayDequeTest {

    static <E>void addFirstHelper(MyArrayDeque<E> deque, E [] arr){
        for(E e : arr){
            deque.addFirst(e);
        }
    }
    static <E>void addLastHelper(MyArrayDeque<E> deque, E [] arr){
        for(E e : arr){
            deque.addLast(e);
        }
    }

    @Test
    void addTest() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<>();
        addFirstHelper(deque,new Integer[]{10,9,8,7,6,5,4,3,2,1});
        addLastHelper(deque,new Integer[]{11,12,13,14,15,16,17,18,19,20});
        deque.printDeque();
    }

    @Test
    void removeTest() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<>();
        Integer[] arr = new Integer[16];
        for(int i=0;i<16;i++){
            arr[i]=i+1;
        }
        addLastHelper(deque,arr);

        for (int i = 0; i < 8; i++) {
            System.out.println(deque.removeFirst());
        }
        deque.printDeque();
        for (int i = 0; i < 8; i++) {
            System.out.println(deque.removeLast());
        }
        deque.printDeque();

    }

    @Test
    void removeTest2(){
        MyArrayDeque<Integer> deque = new MyArrayDeque<>();
        deque.addFirst(1);
        deque.removeFirst();
        Assertions.assertThrows(IllegalStateException.class, deque::removeFirst);
        Assertions.assertThrows(IllegalStateException.class, deque::removeLast);
    }

    @Test
    void printDeque() {
        MyArrayDeque<Integer> deque = new MyArrayDeque<>();
        addFirstHelper(deque,new Integer[]{10,9,8,7,6,5,4,3,2,1});
        deque.printDeque();
        addLastHelper(deque,new Integer[]{11,12,13,14,15,16,17,18,19,20});
        deque.printDeque();
    }

    @Test
    void get(){
        MyArrayDeque<Integer> deque = new MyArrayDeque<>();
        addFirstHelper(deque,new Integer[]{10,9,8,7,6,5,4,3,2,1});
        System.out.println(deque.get(0));
        System.out.println(deque.get(1));
        System.out.println(deque.get(deque.size()-1));
        Assertions.assertThrows(IndexOutOfBoundsException.class,()->{deque.get(-1);});
    }
}