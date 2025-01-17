package com.lordbao.list.impl.sqlList;

import org.junit.jupiter.api.Test;

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
    void  initList(){
        list = new MySqlList<>(new Integer[]{1,3,5});
        System.out.println(list);

        list = new MySqlList<>(list);
        System.out.println(list);
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

    @Test
    void iterator() {
        setUp();
        for (Integer e :list){
            System.out.println(e);
        }
    }
}