package com.lordbao.impl.linkedList;

import com.lordbao.utils.LinkedListInitType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Lord_Bao
 * @Date 2024/10/11 19:45
 * @Version 1.0
 */
class MyLinkedListTest {

    @Test
    void initList(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        MyLinkedList<Integer> myLinkedList1 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, list);
        MyLinkedList<Integer> myLinkedList2 = new MyLinkedList<>(LinkedListInitType.HEAD_INSERT, list);

        System.out.println(myLinkedList1);
        System.out.println(myLinkedList2);
    }

    @Test
    void insertList() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.insertList(0,1);
        myLinkedList.insertList(1,2);
        System.out.println(myLinkedList);
    }

    @Test
    void deleteList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        MyLinkedList<Integer> myLinkedList1 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, list);

        myLinkedList1.deleteList(0);
        myLinkedList1.deleteList(0);
        System.out.println(myLinkedList1);
    }

    @Test
    void locateElem() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        MyLinkedList<Integer> myLinkedList1 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, list);

        System.out.println(myLinkedList1.locateElem(1));
        System.out.println(myLinkedList1.locateElem(-1));
    }

    @Test
    void getElem() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        MyLinkedList<Integer> myLinkedList1 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, list);

        System.out.println(myLinkedList1.getElem(0));
        System.out.println(myLinkedList1.getElem(3));
    }

    @Test
    void addFirst() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        MyLinkedList<Integer> myLinkedList1 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, list);
        myLinkedList1.addFirst(0);
        System.out.println(myLinkedList1);
    }

    @Test
    void removeFirst() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        MyLinkedList<Integer> myLinkedList1 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, list);
        System.out.println(myLinkedList1.removeFirst());
        System.out.println(myLinkedList1);
    }
}