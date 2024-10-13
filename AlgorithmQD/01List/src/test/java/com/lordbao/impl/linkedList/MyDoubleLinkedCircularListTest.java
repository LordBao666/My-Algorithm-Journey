package com.lordbao.impl.linkedList;

import com.lordbao.utils.LinkedListInitType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Lord_Bao
 * @Date 2024/10/13 15:28
 * @Version 1.0
 */

class MyDoubleLinkedCircularListTest {

    @Test
    void initList(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new  MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, list);
        MyDoubleLinkedCircularList<Integer> myLinkedList2 = new  MyDoubleLinkedCircularList<>(LinkedListInitType.HEAD_INSERT, list);

        System.out.println(myLinkedList1);
        System.out.println(myLinkedList2);
    }



    @Test
    void insertList() {
        MyDoubleLinkedCircularList<Integer> list = new MyDoubleLinkedCircularList<>();
        list.insertList(0,6);
        list.insertList(0,5);
        list.insertList(0,3);
        list.insertList(0,1);

        list.insertList(list.listLength(),6);
        list.insertList(list.listLength(),5);
        list.insertList(list.listLength(),3);
        list.insertList(list.listLength(),1);

        System.out.println(list);
    }

    @Test
    void deleteList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new  MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, list);

        System.out.println(myLinkedList1.deleteList(0));
        System.out.println(myLinkedList1.deleteList(0));
        System.out.println(myLinkedList1.deleteList(0));
        System.out.println(myLinkedList1.deleteList(0));
        System.out.println(myLinkedList1);
    }

    @Test
    void locateElem() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, list);

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
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, list);

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
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, list);
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
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, list);
        System.out.println(myLinkedList1.removeFirst());
        System.out.println(myLinkedList1);
    }

    @Test
    void addLast() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, list);
        myLinkedList1.addLast(9);
        System.out.println(myLinkedList1);
    }

    @Test
    void removeLast() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, list);
        System.out.println(myLinkedList1.removeLast());
        System.out.println(myLinkedList1);
    }
}