package com.lordbao.list.impl.linkedList;

import com.lordbao.list.utils.LinkedListInitType;
import org.junit.jupiter.api.Test;

/**
 * @Author Lord_Bao
 * @Date 2024/10/13 15:28
 * @Version 1.0
 */

class MyDoubleLinkedCircularListTest {

    @Test
    void initList(){
        //测试参数类型为 E [] 的有参构造函数
        Integer [] arr ={1,3,5,6};
        MyDoubleLinkedCircularList<Integer> list1 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, arr);
        MyDoubleLinkedCircularList<Integer> list2 = new MyDoubleLinkedCircularList<>(LinkedListInitType.HEAD_INSERT, arr);
        System.out.println(list1);
        System.out.println(list2);

        //测试参数类型为 MyList 的 有参构造函数
        MyDoubleLinkedCircularList<Integer> list3 = new MyDoubleLinkedCircularList<>(LinkedListInitType.TAIL_INSERT, list1);
        MyDoubleLinkedCircularList<Integer> list4 = new MyDoubleLinkedCircularList<>(LinkedListInitType.HEAD_INSERT, list1);
        System.out.println(list3);
        System.out.println(list4);
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
    void testTraverse(){
        MyDoubleLinkedCircularList<Integer> list = new MyDoubleLinkedCircularList<>(new Integer[]{1,3,5,6});
        for(Integer e : list){
            System.out.println(e);
        }
    }
}