package com.lordbao.list.impl.sqlList;

import com.lordbao.list.utils.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * @Author Lord_Bao
 * @Date 2024/10/2 22:19
 * @Version 1.0
 */
class IntSqlListTest {

    private IntSqlList list;
    @BeforeEach
    void setUp() {
        list = new IntSqlList();
        for(int i=0;i<10;i++){
            list.insertList(i,i);
        }
    }

    @Test
    void insertList() {
        Assertions.assertSame(Status.OVERFLOW, list.insertList(-1, -1));

        list.insertList(0,-1);
        System.out.println(list);
        System.out.println(list.listLength());
    }

    @Test
    void deleteList() {
        Integer result = list.deleteList(0);
        System.out.println(list);
        System.out.println(list.listLength());
        System.out.println(result);
    }
}