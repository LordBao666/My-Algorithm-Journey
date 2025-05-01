package com.lordbao.graph.minTree;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Lord_Bao
 * @Date 2025/4/29 11:35
 * @Version 1.0
 * <p>
 * 最小生成树的Prim算法
 * 测试链接 https://www.luogu.com.cn/problem/P3366
 */
public class Prim {


    private static final int MAX_EDGE = 200001;
    //小根堆
    //小根堆存储的是 int [] ,大小为2即可, 0 代表 去向节点, 1代表权重
    //也可以是大小为3, 额外添加一条 源节点 from. 但是这里其实不需要from
    private static final int[][] heap = new int[MAX_EDGE][2];
    private static int heapSize = 0;//堆大小

    private static final int MAX_NODE = 5001;
    //set[i]=true 表示已经加到集合
    private static boolean[] set = new boolean[MAX_NODE];


    //小根堆加边
    private static void offer(int[] edge) {
        int index = heapSize;//edge最终将插入在index位置
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            int[] parentElem = heap[parentIndex];
            //edge的权重更低
            if (edge[1] < parentElem[1]) {
                heap[index] = parentElem;
                index = parentIndex;
            } else {
                break;
            }
        }
        heap[index] = edge;
        heapSize++;
    }

    //弹出小根堆堆顶元素,假设小根堆不为空
    private static int[] pop() {
        int[] ans = heap[0];
        heapSize--;
        int[] edge = heap[heapSize];
        int index = 0;//index是edge最终插入的位置
        int firstLeaf = heapSize >> 1;//首叶位置
        while (index < firstLeaf) {//index有孩子
            int minChildIndex = (index << 1) + 1;
            int[] minChildElem = heap[minChildIndex];
            //右孩子存在并且权重更小
            if (minChildIndex + 1 < heapSize && heap[minChildIndex + 1][1] < minChildElem[1]) {
                minChildIndex = minChildIndex + 1;
                minChildElem = heap[minChildIndex];
            }

            //孩子更小
            if (minChildElem[1] < edge[1]) {
                heap[index] = minChildElem;
                index = minChildIndex;
            } else {
                break;
            }
        }

        heap[index] = edge;
        return ans;
    }

    public static boolean isHeapEmpty() {
        return heapSize == 0;
    }


    public static void build(int nodeNum) {
        heapSize = 0;
//        +1是因为实际上题目要求0编号不用
        Arrays.fill(set, 0, nodeNum + 1, false);
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

        //int[]是大小为2.   0 代表 去向节点, 1代表权重
        //也可以是大小为3, 额外添加一条 源节点 from. 但是这里其实不需要from
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        //+1是因为实际上题目要求0编号不用.后面也要注意
        for (int i = 0; i < nodeNum + 1; i++) {
            graph.add(new ArrayList<>());
        }


        for (int i = 0; i < edgeNum; i++) {
            in.nextToken();
            int from = (int) in.nval;
            in.nextToken();
            int to = (int) in.nval;
            in.nextToken();
            int edgeWeight = (int) in.nval;
            //无向图,实际上要加2条边.
            graph.get(from).add(new int[]{to, edgeWeight});
            graph.get(to).add(new int[]{from, edgeWeight});
        }


        int minTreeWeight = 0;//最小生成树的权重
        int counts = 0;//记录已经添加边的数目
        int startNode = 1;//初始节点选为1(此处不能选0, 因为0标号未用)
        for (int[] edge : graph.get(startNode)) {
            offer(edge);
        }
        set[startNode] = true;

        //nodeNum-1为 最小生成树的边数
        while (!isHeapEmpty() && counts < nodeNum - 1) {
            int[] edge = pop();
            if (!set[edge[0]]) {
                set[edge[0]] = true;
                minTreeWeight += edge[1];
                counts++;
                for (int[] nei : graph.get(edge[0])) {
                    //不加入无效边(代码逻辑上 不要这个判断也不会出错)
                    if (!set[nei[0]]) {
                        offer(nei);
                    }
                }
            }
        }


        out.println(counts == nodeNum - 1 ? minTreeWeight : "orz");
        out.flush();
        out.close();
        bf.close();

    }


}
