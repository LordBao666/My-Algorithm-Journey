package com.lordbao.demo.polyDemo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Lord_Bao
 * @Date 2024/10/15 13:50
 * @Version 1.0
 *
 * 多项式节点
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PolyNode {
    /**多项式系数*/
    private double coef;
    /**多项式指数*/
    private int expn;
}
