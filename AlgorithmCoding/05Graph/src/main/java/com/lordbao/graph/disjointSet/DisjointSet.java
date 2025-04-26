package com.lordbao.graph.disjointSet;


import java.util.Arrays;

/**
 * @Author Lord_Bao
 * @Date 2025/2/17 19:20
 * @Version 1.0
 *
 * 这是并查集的一个标准模版.当然,你还可以引入其他东西,比如连通分量数目
 */
public class DisjointSet {

    //如果parent[a]=负值,则表示以a为根节点的树大小为 |负值|
    //如果parent[a]=非负值,表示a的父节点为该非负值
    private int[] parent;

    public DisjointSet(int N) {
        this.parent = new int[N];
        Arrays.fill(parent, -1);
    }

    //找到a的根节点,并将沿途节点都指向根节点
    private int findRoot(int a) {
        int root = a;
        //先找到根节点
        while (parent[root] >= 0) {
            root = parent[root];
        }

        //将沿途的节点都指向root
        while (parent[a] >= 0) {
            int p = parent[a];
            parent[a] = root;
            a = p;
        }
        return root;
    }

    public boolean isSameSet(int a, int b) {
        return findRoot(a) == findRoot(b);
    }

    public void union(int a, int b) {
        int rootA = findRoot(a);
        int rootB = findRoot(b);
        //已经在同一集合
        if (rootA == rootB) {
            return;
        }

        //这里仍然考虑小挂大
        int sizeA = -parent[rootA];
        int sizeB = -parent[rootB];
        if (sizeA < sizeB) {
            parent[rootB] += parent[rootA];
            parent[rootA] = rootB;
        } else {
            parent[rootA] += parent[rootB];
            parent[rootB] = rootA;
        }

    }
}
