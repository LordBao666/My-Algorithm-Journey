package com.lordbao.list.demo.polyDemo;

import com.lordbao.list.impl.sqlList.MySqlList;
import org.junit.jupiter.api.Test;

/**
 * @Author Lord_Bao
 * @Date 2024/10/15 14:52
 * @Version 1.0
 */
class PolyUtilsTest {

    @Test
    void addPoly() {
        PolyNode[] arr1 = {
                new PolyNode(10, 0),
                new PolyNode(5, 1),
                new PolyNode(-4, 2),
                new PolyNode(3, 3),
                new PolyNode(2, 4)
        };
        PolyNode[] arr2 = {
                new PolyNode(-3, 0),
                new PolyNode(8, 1),
                new PolyNode(4, 2),
                new PolyNode(-5, 4),
                new PolyNode(7, 5),
                new PolyNode(-2, 6)
        };

        MySqlList<PolyNode> list1 = new MySqlList<>(arr1);
        MySqlList<PolyNode> list2 = new MySqlList<>(arr2);

        MySqlList<PolyNode> result = PolyUtils.addPoly(list1, list2);
        System.out.println(result);
    }
}