package com.lordbao.graph;


import java.util.Arrays;

/**
 * @Author Lord_Bao
 * @Date 2025/4/28 16:44
 * @Version 1.0
 *
 *
 * 链式前向星建图
 */
public class CreateGraph {

    // 点的最大数量
    public static int MAX_NODE = 11;

    // 边的最大数量
    // 只有链式前向星方式建图需要这个数量
    // 注意如果无向图的最大数量是m条边，数量要准备m*2
    // 因为一条无向边要加两条有向边
    public static int MAX_EDGE = 21;


    // 链式前向星方式建图
    public static int[] head = new int[MAX_NODE];

    public static int[] next = new int[MAX_EDGE];

    public static int[] to = new int[MAX_EDGE];

    // 如果边有权重，那么需要这个数组
    public static int[] edgeWeight = new int[MAX_EDGE];

    // 如果节点有权重, 那么需要这个数组
    public static int[] nodeWeight = new int[MAX_NODE];

    public static int cnt;//要分配的边号


    //图有n个节点
    public static void build(int n) {
        // 链式前向星清空
        cnt = 1;
        Arrays.fill(head, 1, n + 1, 0);
    }

    // 链式前向星加边
    public static void addEdge(int u, int v, int w) {
        // u -> v , 边权重是w
        next[cnt] = head[u];
        to[cnt] = v;
        edgeWeight[cnt] = w;
        head[u] = cnt++;
    }

    //访问以第u个节点为起始节点的所有邻接边
    public static void visitNeighbors(int u) {
        for(int edge=head[u];edge>0;edge=next[edge]){
            int v = to[edge];
            //打印邻接边和该边的权重
            System.out.println(u+"->"+v+":"+edgeWeight[edge]);
        }
    }

    public static void traversal(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print(i + "(邻居、边权) : ");
            // 注意这个for循环，链式前向星的方式遍历
            for (int ei = head[i]; ei > 0; ei = next[ei]) {
                System.out.print("(" + to[ei] + "," + edgeWeight[ei] + ") ");
            }
            System.out.println();
        }
    }

}
