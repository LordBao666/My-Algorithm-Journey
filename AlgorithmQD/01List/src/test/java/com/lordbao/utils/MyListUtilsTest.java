package com.lordbao.utils;

import com.lordbao.MyList;
import com.lordbao.impl.linkedList.MyDoubleLinkedCircularList;
import com.lordbao.impl.linkedList.MyLinkedList;
import com.lordbao.impl.sqlList.MySqlList;
import org.junit.jupiter.api.Test;

/**
 * @Author Lord_Bao
 * @Date 2024/10/13 22:56
 * @Version 1.0
 */
class MyListUtilsTest {

    @Test
    void mergeSortedListArr() {
        Integer [] arrA ={1,7,8};
        Integer [] arrB ={2,4,6,8,11};
        MyDoubleLinkedCircularList<Integer> list = MyListUtils.mergeSortedList(arrA, arrB);
        System.out.println(list);
    }

    @Test
    void testMergeSortedListByMyDoubleLinkedCircularList() {
        MyDoubleLinkedCircularList <Integer> list1 = new MyDoubleLinkedCircularList<>();
        MyDoubleLinkedCircularList <Integer> list2 = new MyDoubleLinkedCircularList<>();
        MyList<Integer> myList = MyListUtils.mergeSortedList(list1, list2);
        System.out.println(myList.getClass().getSimpleName());
        System.out.println(myList);
        System.out.println("----------");

        list1 = new MyDoubleLinkedCircularList<>();
        list2 = new MyDoubleLinkedCircularList<>(new Integer[]{1,3,5});
        System.out.println(MyListUtils.mergeSortedList(list1,list2));

        list1 = new MyDoubleLinkedCircularList<>(new Integer[]{1,3,5});
        list2 = new MyDoubleLinkedCircularList<>();
        System.out.println(MyListUtils.mergeSortedList(list1,list2));

        list1 = new MyDoubleLinkedCircularList<>(new Integer[]{1,3,5});
        list2 = new MyDoubleLinkedCircularList<>(new Integer[]{2,4,8});
        System.out.println(MyListUtils.mergeSortedList(list1,list2));

    }

    @Test
    void testMergeSortedList2ByMyDoubleLinkedCircularList() {
        MyDoubleLinkedCircularList <Integer> list1 = new MyDoubleLinkedCircularList<>(new Integer[]{1,3,5,6,7});
        MyDoubleLinkedCircularList <Integer> list2 =  new MyDoubleLinkedCircularList<>(new Integer[]{2,4,8,9});
        System.out.println(MyListUtils.mergeSortedList(list1,list2));


        list1 = new MyDoubleLinkedCircularList<>(new Integer[]{1,3,5,7});
        list2 = new MyDoubleLinkedCircularList<>(new Integer[]{9,10,11,14});
        System.out.println(MyListUtils.mergeSortedList(list1,list2));
    }


    @Test
    void testMergeSortedListByMySqlList() {
        MySqlList <Integer> list1 = new MySqlList<>();
        MySqlList <Integer> list2 = new MySqlList<>();
        MyList<Integer> myList = MyListUtils.mergeSortedList(list1, list2);
        System.out.println(myList.getClass().getSimpleName());
        System.out.println(myList);
        System.out.println("----------");


        list1 = new MySqlList<>(new Integer[]{1,3,5});
        list2 = new MySqlList<>();
        System.out.println(MyListUtils.mergeSortedList(list1,list2));

        list1 = new MySqlList<>();
        list2 = new MySqlList<>(new Integer[]{1,3,5});
        System.out.println(MyListUtils.mergeSortedList(list1,list2));

        list1 = new MySqlList<>(new Integer[]{1,3,5});
        list2 = new MySqlList<>(new Integer[]{2,4,8});
        System.out.println(MyListUtils.mergeSortedList(list1,list2));


        list1 = new MySqlList<>(new Integer[]{1,3,5,7});
        list2 = new MySqlList<>(new Integer[]{9,10,11,14});
        System.out.println(MyListUtils.mergeSortedList(list1,list2));
    }



    @Test
    void testMergeSortedListByMyLinkedList() {
        MyLinkedList <Integer> list1 = new MyLinkedList<>();
        MyLinkedList<Integer> list2 = new MyLinkedList<>();
        MyList<Integer> myList = MyListUtils.mergeSortedList(list1, list2);
        System.out.println(myList.getClass().getSimpleName());
        System.out.println(myList);
        System.out.println("----------");


        list1 = new MyLinkedList<>(new Integer[]{1,3,5});
        list2 = new MyLinkedList<>();
        System.out.println(MyListUtils.mergeSortedList(list1,list2));

        list1 = new MyLinkedList<>();
        list2 = new MyLinkedList<>(new Integer[]{1,3,5});
        System.out.println(MyListUtils.mergeSortedList(list1,list2));

        list1 = new MyLinkedList<>(new Integer[]{1,3,5});
        list2 = new MyLinkedList<>(new Integer[]{2,4,8});
        System.out.println(MyListUtils.mergeSortedList(list1,list2));


        list1 = new MyLinkedList<>(new Integer[]{1,3,5,7});
        list2 = new MyLinkedList<>(new Integer[]{9,10,11,14});
        System.out.println(MyListUtils.mergeSortedList(list1,list2));
    }
}