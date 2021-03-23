package com.hjn.dataStructure.graph;

/**
 * @time: 2021.3.14
 * @author HJN
 */
public class ListUDG {

    private VNode[] mVexs; //顶点数组

    // 邻接表中表对应的链表的顶点
    private class ENode{
        int ivex; // 该边所指向的d顶点的位置(该节点所对应的顶点在vexs中的索引)
        ENode nextEdge; // 指向下一条边的指针
    }
    // 邻接表中表的顶点
    private class VNode{
        char data; // 顶点信息
        ENode firstEdge; // 指向第一条依附该顶点的边,表头指针
    }

    /*
     * 创建图(用已提供的矩阵)
     *
     * 参数说明：
     *     vexs  -- 顶点数组
     *     edges -- 边数组
     */
    public ListUDG(char[] vexs, char[][] edges) {

        // 初始化"顶点数"和"边数"
        int vlen = vexs.length;
        int elen = edges.length;

        // 初始化"顶点"
        mVexs = new VNode[vlen];
        for (int i = 0; i < mVexs.length; i++) {
            mVexs[i] = new VNode();
            mVexs[i].data = vexs[i];
            mVexs[i].firstEdge = null;
        }

        // 初始化"边"
        for (int i = 0; i < elen; i++) {
            // 读取边的起始顶点和结束顶点
            char c1 = edges[i][0];
            char c2 = edges[i][1];
            // 读取边的起始顶点和结束顶点
            int p1 = getPosition(edges[i][0]);
            int p2 = getPosition(edges[i][1]);
            // 初始化node1
            ENode node1 = new ENode();
            node1.ivex = p2;
            // 将node1链接到"p1所在链表的末尾"
            if(mVexs[p1].firstEdge == null)
                mVexs[p1].firstEdge = node1;
            else
                linkLast(mVexs[p1].firstEdge, node1);
            // 初始化node2
            ENode node2 = new ENode();
            node2.ivex = p1;
            // 将node2链接到"p2所在链表的末尾"
            if(mVexs[p2].firstEdge == null)
                mVexs[p2].firstEdge = node2;
            else
                linkLast(mVexs[p2].firstEdge, node2);

        }
    }

    /*
     * 返回ch位置
     */
    private int getPosition(char ch) {
        for(int i=0; i<mVexs.length; i++)
            if(mVexs[i].data==ch)
                return i;
        return -1;
    }

    /*
     * 将node节点链接到list的最后
     */
    private void linkLast(ENode list, ENode node) {
        ENode p = list;

        while(p.nextEdge!=null)
            p = p.nextEdge;
        p.nextEdge = node;
    }

    /**
     * 深度优先遍历图——无向图
     * 1、如果图是顺序存储节点的，则从图的第一个节点开始遍历
     * 2、如果该节点没有被访问，则访问该节点
     * 3、继续访问该节点的邻接节点，重复2
     * 4、如果该节点的邻接节点都访问完了，return到上一级
     *
     * 深度优先遍历图——有向图
     * 1、同上
     * 2、同上
     * 3、继续访问该节点的出边的邻接节点，重复2
     * 4、同上
     */
    private void DFS() {
        // 初始化visited数组
        boolean[] visited = new boolean[mVexs.length];
        for(int i = 0; i<mVexs.length; i++) {
            visited[i] = false;
        }

        // 从第一个顶点开始遍历
        System.out.println("DFS:");
        for(int i=0; i<mVexs.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                DFS(i,visited);
            }
        }
    }

    // 递归实现
    private void DFS(int i, boolean[] visited) {
        System.out.print(mVexs[i].data+" ");
        ENode e = mVexs[i].firstEdge;
        while (e!=null) {
            if(!visited[e.ivex]){
                visited[e.ivex] = true; // 错误：忘了设置visited = true
                DFS(e.ivex,visited); // 深度！
            }
            e = e.nextEdge;
        }
    }


    /**
     * 广度优先搜索遍历图的过程是以v为起点，由近至远，依次访问和v有路径相通且路径长度为1,2...的顶点。
     */
    

    // 打印出邻接表
    private static void printGraph(ListUDG pG) {
        for(VNode i:pG.mVexs) {
            System.out.println(i.data+":");
            ENode eNode = i.firstEdge;
            while (eNode!=null) {
                System.out.print(eNode.ivex);
                eNode=eNode.nextEdge;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[][] edges = new char[][]{
                {'A', 'C'},
                {'A', 'D'},
                {'A', 'F'},
                {'B', 'C'},
                {'C', 'D'},
                {'E', 'G'},
                {'F', 'G'}};
        ListUDG pG;

        pG = new ListUDG(vexs, edges);
//        printGraph(pG); // 打印出邻接表
        pG.DFS();
    }
}