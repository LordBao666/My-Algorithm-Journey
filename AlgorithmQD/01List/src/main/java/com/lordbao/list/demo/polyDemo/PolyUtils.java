package com.lordbao.list.demo.polyDemo;


import com.lordbao.list.impl.sqlList.MySqlList;

/**
 * @Author Lord_Bao
 * @Date 2024/10/15 13:48
 * @Version 1.0
 */
public class PolyUtils {
    /**
     * 多项式相加
     */
    public static MySqlList<PolyNode> addPoly(MySqlList<PolyNode> list1,
                                       MySqlList<PolyNode> list2) {
        if (list1.isEmpty()) {
            return new MySqlList<>(list2);
        }
        if (list2.isEmpty()) {
            return new MySqlList<>(list1);
        }

        int i = 0, j = 0;
        MySqlList<PolyNode> list = new MySqlList<>();

        while (i < list1.listLength() && j < list2.listLength()) {
            PolyNode p = list1.getElem(i);
            PolyNode q = list2.getElem(j);
            if(p.getExpn()==q.getExpn()){
                double result = p.getCoef() + q.getCoef();
                //加法结果小于10^-5 次方，则忽略不计
                if(Math.abs(result)>=1e-5){
                    list.addLast(new PolyNode(result,p.getExpn()));
                }
                i++;
                j++;
            } else if (p.getExpn() < q.getExpn()) {
                list.addLast(new PolyNode(p.getCoef(),p.getExpn()));
                i++;
            }else {
                list.addLast(new PolyNode(q.getCoef(),q.getExpn()));
                j++;
            }
        }

        while (i<list1.listLength()){
            PolyNode p = list1.getElem(i);
            list.addLast(new PolyNode(p.getCoef(),p.getExpn()));
            i++;
        }
        while (j<list2.listLength()){
            PolyNode q = list2.getElem(j);
            list.addLast(new PolyNode(q.getCoef(),q.getExpn()));
            j++;
        }

        return list;
    }


}
