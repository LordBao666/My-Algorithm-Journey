package com.lordbao.graph.minTree;


import java.io.*;
import java.util.Arrays;

/**
 * @Author Lord_Bao
 * @Date 2025/4/29 11:35
 * @Version 1.0
 * <p>
 * 最小生成树的Kruskal算法
 * 测试链接 https://www.luogu.com.cn/problem/P3366
 */
public class Kruskal {

    private static final int MAX_EDGE = 200001;
    private static final int[][] edges = new int[MAX_EDGE][3];


    private static final int MAX_NODE = 5001;
    private static final int[] parent = new int[MAX_NODE];

    private static int findRoot(int u) {
        int root = u;
        for (int t = parent[root]; t >= 0; ) {
            root = t;
            t = parent[root];
        }

        for (int t = parent[u]; t >= 0; ) {
            parent[u] = root;
            u = t;
            t = parent[u];
        }

        return root;
    }


    //合并u和v,假设u和v已经处于一个联通分量,那么返回false,否则返回true.
    //这里就不做小挂大的优化了...
    private static boolean union(int u, int v) {
        int root1 = findRoot(u);
        int root2 = findRoot(v);
        if (root1 != root2) {
            parent[root1] = root2;
            return true;
        }
        return false;
    }


    public static void build(int nodeNum) {
        Arrays.fill(parent, 0, nodeNum, -1);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(bf);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        in.nextToken();
        int nodeNum = (int) in.nval;//节点数
        in.nextToken();
        int edgeNum = (int) in.nval;//无向边数

        //初始化
        build(nodeNum);

        for (int i = 0; i < edgeNum; i++) {
            in.nextToken();
            int from = (int) in.nval;
            in.nextToken();
            int to = (int) in.nval;
            in.nextToken();
            int edgeWeight = (int) in.nval;

            edges[i][0] = from;
            edges[i][1] = to;
            edges[i][2] = edgeWeight;
        }

        //对边进行排序
        Arrays.sort(edges, 0, edgeNum, (e1, e2) -> e1[2] - e2[2]);

        int minTreeWeight = 0;//最小生成树的权重
        int counts = 0;//记录已经添加边的数目
        for (int i = 0; counts < nodeNum - 1 && i < edgeNum; i++) {//nodeNum-1 表示最小生成树的边数
            //合并前不属于同一个集合,则加边成功
            if (union(edges[i][0], edges[i][1])) {
                counts++;
                minTreeWeight += edges[i][2];
            }
        }

        out.println(counts == nodeNum - 1 ? minTreeWeight : "orz");
        out.flush();
        out.close();
        bf.close();

    }
}
