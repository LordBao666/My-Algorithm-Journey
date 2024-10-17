package com.lordbao.deque;


import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;

/**
 * @Author Lord_Bao
 * @Date 2024/10/15 17:41
 * @Version 1.0
 */
@Slf4j
public abstract class MyAbstractDeque<E> implements MyDeque<E>{

    @Override
    public void printDeque() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append("[");
        for (E e : this) {
            sb.append(e).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }


    @Override
    public E get(int i){
        if(i<0 || i>=size()){
            log.error("index {} is not between {} and {}",0,size()-1);

            String error="index is not between 0 and" + (size()-1);
            throw new IndexOutOfBoundsException(error);
        }
        Iterator<E> iterator = iterator();
        int count = 0;
        E e=iterator.next();
        while (count<i){
            e=iterator.next();
            count++;
        }
        return e;
    }
}
