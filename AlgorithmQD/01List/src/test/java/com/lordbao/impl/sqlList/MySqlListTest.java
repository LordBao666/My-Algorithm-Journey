package com.lordbao.impl.sqlList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Lord_Bao
 * @Date 2024/10/10 20:32
 * @Version 1.0
 */
class MySqlListTest {

    private MySqlList<Integer> list;

    void setUp() {
        list = new MySqlList<>();
        for(int i=0;i<17;i++){
            list.insertList(i,i);
        }
    }

    @Test
    void insertList() {

        list = new MySqlList<>();
        for(int i=0;i<17;i++){
            list.insertList(i,i);
        }
    }

    @Test
    void deleteList() {
        setUp();
        for(int i=0;i<16;i++){
            list.deleteList(0);
        }
    }
}