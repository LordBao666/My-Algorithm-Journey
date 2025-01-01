package com.lordbao.tree.trie;

import java.io.*;
import java.util.Arrays;

/***
 * 静态数组实现
 * 参见左神的 https://github.com/algorithmzuo/algorithm-journey/blob/main/src/class044/Code02_TrieTree.java
 *
 *
 * 字典树允许插入一个字符串多次.
 * 字典树的操作的时间复杂度取决于单词的长度.
 *
 * 测试链接 https://www.nowcoder.com/practice/7f8a8553ddbf4eaab749ec988726702b
 * 将Trie改为Main去测试
 */
public class Trie {


    //自己去测试,如果过不了,就调大一点
    public static int MAX_N = 15_0000;
    public static int [][] tree = new int[MAX_N][26];
    public static int [] pass = new int[MAX_N];
    public static int [] end = new int[MAX_N];

    //指向最后申请的空间
    //空间0弃而不用, 空间1对应 空字符串 ""
    public static int cnt=1;


    //初始化
    public static  void init(){
        cnt=1;
    }
    //空间回收
    public static void clear(){
        for(int i=1;i<=cnt;i++){
            Arrays.fill(tree[i],0);
        }
        Arrays.fill(pass,1,cnt+1,0);
        Arrays.fill(end,1,cnt+1,0);
    }

    public static void insert(String word){
        int cur = 1;
        pass[cur]++;

        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(tree[cur][c-'a']==0){
                tree[cur][c-'a']=++cnt;//开辟新空间存c
            }
            cur = tree[cur][c-'a'];//移动到存c的空间
            pass[cur]++;
        }
        end[cur]++;
    }

    //返回word的出现次数
    public static int search(String word){
        int cur=1;
        for(int i=0;i<word.length();i++){
            int next = tree[cur][word.charAt(i)-'a'];
            if(next==0){//不是哥们,都没存你
                return 0;
            }
            cur=next;
        }
        return end[cur];
    }


    //返回word为前缀的出现次数
    public static int prefixNumber(String word){
        int cur=1;
        for(int i=0;i<word.length();i++){
            int next = tree[cur][word.charAt(i)-'a'];
            if(next==0){//不是哥们,都没存你
                return 0;
            }
            cur=next;
        }
        return pass[cur];
    }

    public static void delete(String word){
        if(search(word)>0){//有才删
            int cur=1;
            for(int i=0;i<word.length();i++){
                //删完后发现为0,直接tree[cur][word.charAt(i)-'a']=0,return
                //因为静态实现不存在空间回收,删除空间不再用
                if(--pass[tree[cur][word.charAt(i)-'a']]==0){
                    tree[cur][word.charAt(i)-'a']=0;//断路
                    return;
                }
                cur=tree[cur][word.charAt(i)-'a'];
            }
            end[cur]--;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf =  new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(bf);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        init();//初始化
        in.nextToken();
        int m = (int)in.nval;

        //op代表一个操作码，如果op为1，则代表添加word，op为2则代表删除word，
        //op为3则代表查询word是否在字典树中，op为4代表返回以word为前缀的单词数量（数据保证不会删除不存在的word）
        for(int i=0;i<m;i++){
            in.nextToken();
            int op = (int)in.nval;
            in.nextToken();
            String word = in.sval;

            if(op==1){
                insert(word);
            }else if(op==2){
                delete(word);
            }else if(op==3){
                int num = search(word);
                if(num==0){
                    out.println("NO");
                }else {
                    out.println("YES");
                }
            }else {//op==4
                out.println(prefixNumber(word));
            }
        }


        clear();//回收空间
        out.flush();
        out.close();
        bf.close();
    }

}