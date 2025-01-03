package com.lordbao.list.impl.linkedList;

import com.lordbao.list.utils.LinkedListInitType;
import org.junit.jupiter.api.Test;

/**
 * @Author Lord_Bao
 * @Date 2024/10/11 19:45
 * @Version 1.0
 */
class MyLinkedListTest {

    @Test
    void initList(){
        Integer [] arr ={1,3,5,6};
        MyLinkedList<Integer> list1 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, arr);
        MyLinkedList<Integer> list2 = new MyLinkedList<>(LinkedListInitType.HEAD_INSERT, arr);

        System.out.println(list1);
        System.out.println(list2);


        MyLinkedList<Integer> list3 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, list1);
        MyLinkedList<Integer> list4 = new MyLinkedList<>(LinkedListInitType.HEAD_INSERT, list1);
        System.out.println(list3);
        System.out.println(list4);
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
        Integer [] arr ={1,3,5,6};
        MyLinkedList<Integer> myLinkedList1 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, arr);

        myLinkedList1.deleteList(0);
        myLinkedList1.deleteList(0);
        System.out.println(myLinkedList1);
    }

    @Test
    void locateElem() {
        Integer [] arr ={1,3,5,6};
        MyLinkedList<Integer> myLinkedList1 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, arr);

        System.out.println(myLinkedList1.locateElem(1));
        System.out.println(myLinkedList1.locateElem(-1));
    }

    @Test
    void getElem() {
        Integer [] arr ={1,3,5,6};
        MyLinkedList<Integer> myLinkedList1 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, arr);

        System.out.println(myLinkedList1.getElem(0));
        System.out.println(myLinkedList1.getElem(3));
    }

    @Test
    void addFirst() {
        Integer [] arr ={1,3,5,6};
        MyLinkedList<Integer> myLinkedList1 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, arr);
        myLinkedList1.addFirst(0);
        System.out.println(myLinkedList1);
    }

    @Test
    void removeFirst() {
        Integer [] arr ={1,3,5,6};
        MyLinkedList<Integer> myLinkedList1 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, arr);
        System.out.println(myLinkedList1.removeFirst());
        System.out.println(myLinkedList1);
    }

    @Test
    void iterator() {
        Integer [] arr ={1,3,5,6};
        MyLinkedList<Integer> myLinkedList1 = new MyLinkedList<>(LinkedListInitType.TAIL_INSERT, arr);
        for(Integer ele : myLinkedList1){
            System.out.println(ele);
        }
    }
}