package com.lordbao.utils;

import com.lordbao.impl.linkedList.MyDoubleLinkedCircularList;
import org.junit.jupiter.api.Test;

/**
 * @Author Lord_Bao
 * @Date 2024/10/13 22:56
 * @Version 1.0
 */
class MyListUtilsTest {

    @Test
    void mergeSortedList() {
        Integer [] arrA ={1,7,8};
        Integer [] arrB ={2,4,6,8,11};
        MyDoubleLinkedCircularList<Integer> list = MyListUtils.mergeSortedList(arrA, arrB);
        System.out.println(list);
    }

    @Test
    void testMergeSortedList() {
        MyDoubleLinkedCircularList <Integer> list1 = new MyDoubleLinkedCircularList<>();
        MyDoubleLinkedCircularList <Integer> list2 = new MyDoubleLinkedCircularList<>();
        System.out.println(MyListUtils.mergeSortedList(list1,list2));

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
    void testMergeSortedList2() {
        MyDoubleLinkedCircularList <Integer> list1 = new MyDoubleLinkedCircularList<>(new Integer[]{1,3,5,6,7});;
        MyDoubleLinkedCircularList <Integer> list2 =  new MyDoubleLinkedCircularList<>(new Integer[]{2,4,8,9});
        System.out.println(MyListUtils.mergeSortedList(list1,list2));


        list1 = new MyDoubleLinkedCircularList<>(new Integer[]{1,3,5,7});
        list2 = new MyDoubleLinkedCircularList<>(new Integer[]{9,10,11,14});
        System.out.println(MyListUtils.mergeSortedList(list1,list2));
    }
}