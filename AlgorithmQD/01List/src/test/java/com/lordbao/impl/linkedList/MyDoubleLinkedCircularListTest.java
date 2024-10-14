package com.lordbao.impl.linkedList;

import com.lordbao.utils.LinkedListInitType;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Author Lord_Bao
 * @Date 2024/10/13 15:28
 * @Version 1.0
 */

class MyDoubleLinkedCircularListTest {

    @Test
    void initList(){
        Integer [] arr ={1,3,5,6};
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, arr);
        MyDoubleLinkedCircularList<Integer> myLinkedList2 = new MyDoubleLinkedCircularList<>(LinkedListInitType.HEAD_INSERT, arr);

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
        Integer [] arr ={1,3,5,6};
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, arr);

        System.out.println(myLinkedList1.deleteList(0));
        System.out.println(myLinkedList1.deleteList(0));
        System.out.println(myLinkedList1.deleteList(0));
        System.out.println(myLinkedList1.deleteList(0));
        System.out.println(myLinkedList1);
    }

    @Test
    void locateElem() {
        Integer [] arr ={1,3,5,6};
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, arr);

        System.out.println(myLinkedList1.locateElem(1));
        System.out.println(myLinkedList1.locateElem(-1));
    }

    @Test
    void getElem() {
        Integer [] arr ={1,3,5,6};
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, arr);

        System.out.println(myLinkedList1.getElem(0));
        System.out.println(myLinkedList1.getElem(3));
    }

    @Test
    void addFirst() {
        Integer [] arr ={1,3,5,6};
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, arr);
        myLinkedList1.addFirst(0);
        System.out.println(myLinkedList1);
    }

    @Test
    void removeFirst() {
        Integer [] arr ={1,3,5,6};
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, arr);
        System.out.println(myLinkedList1.removeFirst());
        System.out.println(myLinkedList1);
    }

    @Test
    void addLast() {
        Integer [] arr ={1,3,5,6};
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, arr);
        myLinkedList1.addLast(9);
        System.out.println(myLinkedList1);
    }

    @Test
    void removeLast() {
        Integer [] arr ={1,3,5,6};
        MyDoubleLinkedCircularList<Integer> myLinkedList1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, arr);
        System.out.println(myLinkedList1.removeLast());
        System.out.println(myLinkedList1);
    }

    @Test
    void testIterator(){
        List<Integer> list = List.of(1, 3, 5, 6);
        //for增强循环遍历，本质还是通过迭代器遍历
        for(Integer ele : list){
            System.out.println(ele);
        }

        System.out.println("--------------");
        //迭代器遍历
        java.util.Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    void testTraverse(){

        MyDoubleLinkedCircularList<Integer> list = new MyDoubleLinkedCircularList<>(new Integer[]{1,3,5,6});
        for(Integer e : list){
            System.out.println(e);
        }
    }
}