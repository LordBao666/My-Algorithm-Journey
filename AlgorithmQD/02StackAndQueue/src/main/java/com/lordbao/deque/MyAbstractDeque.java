package com.lordbao.deque;



/**
 * @Author Lord_Bao
 * @Date 2024/10/15 17:41
 * @Version 1.0
 */

public abstract class MyAbstractDeque<E> implements MyDeque<E>{

    @Override
    public void printDeque() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append("[ ");
        for (E e : this) {
            sb.append(e).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }


}
