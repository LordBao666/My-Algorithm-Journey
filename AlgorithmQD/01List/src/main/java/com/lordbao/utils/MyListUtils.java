package com.lordbao.utils;


import com.lordbao.MyList;
import com.lordbao.impl.linkedList.MyDoubleLinkedCircularList;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Objects;

/**
 * @Author Lord_Bao
 * @Date 2024/10/13 22:42
 * @Version 1.0
 * <p>
 * 针对MyList的一些工具类
 */
@Slf4j
public class MyListUtils {
    /**
     * @param arrA 按升序排列的数组
     * @param arrB 按升序排列的数组
     * @param <E>  必须是可比较的
     * @return 合并后的MyDoubleLinkedCircularList
     */
    public static <E extends Comparable<E>> MyDoubleLinkedCircularList<E> mergeSortedList(E[] arrA, E[] arrB) {
        MyDoubleLinkedCircularList<E> list = new MyDoubleLinkedCircularList<>();
        int i = 0, j = 0;
        while (i < arrA.length && j < arrB.length) {
            //arrA[i] 小于  arrB[j]
            if (arrA[i].compareTo(arrB[j]) < 0) {
                list.addLast(arrA[i++]);
            } else {
                list.addLast(arrB[j++]);
            }
        }

        while (i < arrA.length) {
            list.addLast(arrA[i++]);
        }
        while (j < arrB.length) {
            list.addLast(arrB[j++]);
        }
        return list;
    }


    /***
     *
     * 将一个迭代器 Iterator的元素 添加到 MyList的末尾
     */
    private static <E> void addIteratorToMyListAtLast
    (Iterator<E> iterator, MyList<E> list) {
        while (iterator.hasNext()) {
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
    private static <E extends Comparable<E>> void compareAndAddToMyListAtLast(
            E elementA, E elementB, MyList<E> list
    ) {
        if (elementA.compareTo(elementB) < 0) {
            list.addLast(elementA);
            list.addLast(elementB);
        } else {
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
    private static <E extends Comparable<E>> void mergeElementsAndIteratorToMyListAtLast(
            E elementA, E elementB, Iterator<E> iterator, MyList<E> targetList
    ) {
        //如果A为空
        if (elementA == null) {
            if (elementB != null) {
                targetList.addLast(elementB);
            }
            addIteratorToMyListAtLast(iterator, targetList);
            return;
        }

        //如果迭代器没有可迭代的元素
        if (!iterator.hasNext()) {
            if (elementB == null) {
                targetList.addLast(elementA);
            } else {
                compareAndAddToMyListAtLast(elementA, elementB, targetList);
            }
            return;
        }

        //代码行到此处,elementA 必然非空，并且迭代器存在可迭代的元素
        E current = elementB == null ? iterator.next() : elementB;

        //elementA是否已经添加到list
        boolean isAddToListFlag = false;
        while (iterator.hasNext()) {
            if (elementA.compareTo(current) < 0) {
                targetList.addLast(elementA);
                isAddToListFlag = true;
                break;
            } else {
                targetList.addLast(current);
                current = iterator.next();
            }
        }
        if (isAddToListFlag) {
            //将current加入到targetList, 并将iterator剩余元素加入到targetList
            targetList.addLast(current);
            addIteratorToMyListAtLast(iterator, targetList);
        } else {
            //此时iterator已经遍历完毕，仅剩current未比较
            compareAndAddToMyListAtLast(elementA, current, targetList);
        }
    }

    /**
     * @param listA 按升序排列的listA
     * @param listB 按升序排列的listB
     * @param <E>   必须是可比较的
     * @return 返回listA 和 listB 合并后的list。该list的类型设计如下：
     * 1.如果listA 和 listB 类型相同，则返回的list类型与它们相同。
     * 注意！底层会通过反射创建该list实例，如果出现异常，将返回MyDoubleLinkedCircularList。
     * 2.如果listA 和 listB 类型不同，则返回的list类型默认为MyDoubleLinkedCircularList。
     */
    public static <E extends Comparable<E>> MyList<E> mergeSortedList(
            MyList<E> listA, MyList<E> listB) {

        MyDoubleLinkedCircularList<E> list = new MyDoubleLinkedCircularList<>();
        Iterator<E> iteratorA = listA.iterator();
        Iterator<E> iteratorB = listB.iterator();

        //如果迭代器A 和 迭代器B 都没有剩余元素，直接返回list
        if (!iteratorA.hasNext() && !iteratorB.hasNext()) {
            return createInstance(listA.getClass(), listB.getClass(), list);
        }

        //如果仅迭代器A有剩余元素，则直接将它加到list的末尾
        if (iteratorA.hasNext() && !iteratorB.hasNext()) {
            addIteratorToMyListAtLast(iteratorA, list);
            return createInstance(listA.getClass(), listB.getClass(), list);
        }

        //如果仅迭代器B有剩余元素，则直接将它加到list的末尾
        if (iteratorB.hasNext() && !iteratorA.hasNext()) {
            addIteratorToMyListAtLast(iteratorB, list);
            return createInstance(listA.getClass(), listB.getClass(), list);
        }
        //代码行至此处, 迭代器A和迭代器B都必然存在元素

        E currentA = iteratorA.next();
        E currentB = iteratorB.next();

        while (iteratorA.hasNext() && iteratorB.hasNext()) {
            //如果currentA  小于 current B
            if (currentA.compareTo(currentB) < 0) {
                list.addLast(currentA);
                currentA = iteratorA.next();
            } else {
                list.addLast(currentB);
                currentB = iteratorB.next();
            }
        }

        //代码行到此处, 情况分为两种
        //1.iteratorA已经迭代完毕，此时仅剩最新的currentA未比较；
        //2.iteratorB已经迭代完毕，此时仅剩最新的currentB未比较。
        //iteratorA 已经迭代完毕
        if (!iteratorA.hasNext()) {
            mergeElementsAndIteratorToMyListAtLast(currentA, currentB, iteratorB, list);
        } else {//iteratorB 已经迭代完毕
            mergeElementsAndIteratorToMyListAtLast(currentB, currentA, iteratorA, list);
        }

        return createInstance(listA.getClass(), listB.getClass(), list);
    }

    /***
     *
     * 如果 clazzA  和 clazzB的类型相同，则返回该类型的一个空实例。
     * 如果不同 或者说产生异常，则返回一个MyDoubleLinkedCircularList的实例
     */
    @SuppressWarnings("unchecked")
    public static <E> MyList<E> createInstance(Class<? extends MyList> clazzA,
                                               Class<? extends MyList> clazzB) {
        //如果listA 和 listB的类型相同
        if (Objects.equals(clazzA, clazzB)) {
            try {
                //利用反射通过无参构造器创建对象
                return (MyList<E>) clazzA.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                log.error("利用反射通过无参构造器创建 MyList 失败", e);
                return new MyDoubleLinkedCircularList<>();
            }
        } else {
            return new MyDoubleLinkedCircularList<>();
        }
    }

    /***
     * 返回类型：
     *    如果clazzA  和 clazzB的类型相同，则返回该类型。
     *    如果不同，则返回类型为MyDoubleLinkedCircularList。
     * 返回数据：
     *    将sourceList 的 数据都复制给MyList
     *
     * 根据clazzA  和 clazzB 选择合理的MyList，该MyList将携带sourceList的所有数据。
     * 若出现反射异常，返回的类型就是MyDoubleLinkedCircularList.
     */
    @SuppressWarnings("unchecked")
    public static <E> MyList<E> createInstance(Class<? extends MyList> clazzA
            , Class<? extends MyList> clazzB, MyList<E> sourceList) {
        //如果listA 和 listB的类型相同
        if (Objects.equals(clazzA, clazzB)) {
            try {
                //利用反射通过无参构造器创建对象
                return (MyList<E>) clazzA.getDeclaredConstructor(MyList.class).newInstance(sourceList);
            } catch (Exception e) {
                log.error("利用反射通过有参构造器(参数类型MyList)创建 MyList 失败", e);
                return new MyDoubleLinkedCircularList<>(sourceList);
            }
        } else {
            return new MyDoubleLinkedCircularList<>(sourceList);
        }
    }

}
