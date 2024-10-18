package com.lordbao.deque.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Lord_Bao
 * @Date 2024/10/18 10:23
 * @Version 1.0
 */
class MyLinkedListDequeTest {

    private MyLinkedListDeque<Integer>deque = new MyLinkedListDeque<>();

    @BeforeEach
    void setUp(){
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
    }

    @Test
    void printDeque() {
        deque.printDeque();
    }

    @Test
    void addFirst(){
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.printDeque();
    }

    @Test
    void remove(){
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        Assertions.assertThrows(IllegalStateException.class,()->{deque.removeFirst();});
        Assertions.assertThrows(IllegalStateException.class,()->{deque.removeLast();});
    }

    @Test
    void get(){
        System.out.println(deque.get(1));
        System.out.println(deque.get(3));

        Assertions.assertThrows(IndexOutOfBoundsException.class,()->{deque.get(-1);});
        Assertions.assertThrows(IndexOutOfBoundsException.class,()->{deque.get(deque.size());});
    }

    @Test
    void getFirstAndLast(){
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
    }

    @Test
    void test(){
        MyLinkedListDeque<Object> deque1 = new MyLinkedListDeque<>();
        Assertions.assertThrows(IndexOutOfBoundsException.class,()-> deque1.getFirst());
        Assertions.assertThrows(IndexOutOfBoundsException.class,()-> deque1.getLast());
    }
}