package com.lordbao.utils;


import com.lordbao.impl.linkedList.MyDoubleLinkedCircularList;

import java.util.Iterator;

/**
 * @Author Lord_Bao
 * @Date 2024/10/13 22:42
 * @Version 1.0
 *
 * 针对MyList的一些工具类
 */
public class MyListUtils {
    /**
     *
     * @param arrA 按升序排列的数组
     * @param arrB 按升序排列的数组
     * @return 合并后的MyDoubleLinkedCircularList
     * @param <E> 必须是可比较的
     */
    public static <E extends   Comparable<E>> MyDoubleLinkedCircularList<E> mergeSortedList(E [] arrA, E [] arrB){
        MyDoubleLinkedCircularList<E> list = new MyDoubleLinkedCircularList<>();
        int i=0,j=0;
        while (i<arrA.length && j< arrB.length){
            //arrA[i] 小于  arrB[j]
            if(arrA[i].compareTo(arrB[j])<0){
                list.addLast(arrA[i++]);
            }else {
                list.addLast(arrB[j++]);
            }
        }

        while (i<arrA.length){
            list.addLast(arrA[i++]);
        }
        while (j< arrB.length){
            list.addLast(arrB[j++]);
        }
        return list;
    }


    /***
     *
     * 将一个迭代器 Iterator的元素 添加到 MyDoubleLinkedCircularList的末尾
     */
    private static <E> void addIteratorToMyDoubleLinkedCircularListAtLast
    (Iterator<E> iterator, MyDoubleLinkedCircularList<E> list){
        while (iterator.hasNext()){
            list.addLast(iterator.next());
        }
    }

    /***
     *
     * @param elementA 待比较的元素A 假定非空
     * @param elementB 待比较的元素B 假定非空
     * @param list 目标list
     * @param <E> 必须是可比较的
     * 将元素A和元素B的比较结果，按照升序依次添加到list的末尾
     */
    private static <E extends Comparable<E>> void compareAndAddToMyDoubleLinkedCircularListAtLast(
            E elementA ,E elementB , MyDoubleLinkedCircularList<E> list
    ){
        if(elementA.compareTo(elementB)<0){
            list.addLast(elementA);
            list.addLast(elementB);
        }else {
            list.addLast(elementB);
            list.addLast(elementA);
        }
    }

    /***
     *
     * @param elementA 待比较元素, 可以为null
     * @param elementB 来自迭代器的前一个元素，可以为null
     * @param iterator 待比较的迭代器，迭代器不能为null，但是迭代器剩余的元素可以为0
     * @param targetList 目标列表
     * @param <E> 必须是可比较的
     *
     *  elementA 依次与elementB 和   iterator 进行比较，并将结果添加到  targetList的末尾
     */
    private static <E extends Comparable <E> > void mergeElementsAndIteratorToMyDoubleLinkedCircularListAtLast(
            E elementA, E elementB, Iterator<E> iterator, MyDoubleLinkedCircularList<E> targetList
    ){
        //如果A为空
        if(elementA==null){
            if(elementB!=null){
                targetList.addLast(elementB);
            }
            addIteratorToMyDoubleLinkedCircularListAtLast(iterator,targetList);
            return;
        }

        //如果迭代器没有可迭代的元素
        if(!iterator.hasNext()){
            if(elementB==null){
                targetList.addLast(elementA);
            }else {
                compareAndAddToMyDoubleLinkedCircularListAtLast(elementA,elementB,targetList);
            }
            return;
        }

        //代码行到此处,elementA 必然非空，并且迭代器存在可迭代的元素
        E current = elementB==null?iterator.next():elementB;

        //elementA是否已经添加到list
        boolean isAddToListFlag = false;
        while (iterator.hasNext()){
            if(elementA.compareTo(current)<0){
                targetList.addLast(elementA);
                isAddToListFlag=true;
                break;
            }else {
                targetList.addLast(current);
                current= iterator.next();
            }
        }
        if(isAddToListFlag){
            //将current加入到targetList, 并将iterator剩余元素加入到targetList
            targetList.addLast(current);
            addIteratorToMyDoubleLinkedCircularListAtLast(iterator,targetList);
        }else {
            //此时iterator已经遍历完毕，仅剩current未比较
            compareAndAddToMyDoubleLinkedCircularListAtLast(elementA,current,targetList);
        }
    }

    /**
     *
     * @param listA 按升序排列的listA
     * @param listB 按升序排列的listB
     * @return 合并后的MyDoubleLinkedCircularList
     * @param <E> 必须是可比较的
     */
    public static <E extends   Comparable<E>> MyDoubleLinkedCircularList<E> mergeSortedList(
            MyDoubleLinkedCircularList<E> listA, MyDoubleLinkedCircularList<E> listB){

        MyDoubleLinkedCircularList<E> list = new MyDoubleLinkedCircularList<>();
        Iterator<E> iteratorA = listA.iterator();
        Iterator<E> iteratorB = listB.iterator();

        //如果迭代器A 和 迭代器B 都没有剩余元素，直接返回list
        if(!iteratorA.hasNext() && !iteratorB.hasNext()){
            return list;
        }

        //如果仅迭代器A有剩余元素，则直接将它加到list的末尾
        if(iteratorA.hasNext() && !iteratorB.hasNext()){
            addIteratorToMyDoubleLinkedCircularListAtLast(iteratorA,list);
            return list;
        }

        //如果仅迭代器B有剩余元素，则直接将它加到list的末尾
        if(iteratorB.hasNext() && !iteratorA.hasNext()){
            addIteratorToMyDoubleLinkedCircularListAtLast(iteratorB,list);
            return list;
        }
        //代码行至此处, 迭代器A和迭代器B都必然存在元素

        E currentA=iteratorA.next();
        E currentB=iteratorB.next();

        while (iteratorA.hasNext()&& iteratorB.hasNext()){
            //如果currentA  小于 current B
            if(currentA.compareTo(currentB)<0){
                list.addLast(currentA);
                currentA=iteratorA.next();
            }else {
                list.addLast(currentB);
                currentB=iteratorB.next();
            }
        }

        //代码行到此处, 情况分为两种
        //1.iteratorA已经迭代完毕，此时仅剩最新的currentA未比较；
        //2.iteratorB已经迭代完毕，此时仅剩最新的currentB未比较。
        //iteratorA 已经迭代完毕
        if (!iteratorA.hasNext()){
            mergeElementsAndIteratorToMyDoubleLinkedCircularListAtLast(currentA,currentB,iteratorB,list);
        }else {//iteratorB 已经迭代完毕
            mergeElementsAndIteratorToMyDoubleLinkedCircularListAtLast(currentB,currentA,iteratorA,list);
        }

        return list;
    }
}
