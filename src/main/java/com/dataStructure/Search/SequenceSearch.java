package com.dataStructure.Search;

public class SequenceSearch {
    public static int Search_Seq(SSTable ST, int key) {
        ST.elem[0] = key; // 第0个位置不装元素

        int i = 0;
        for (i = ST.TableLen; ST.elem[i] != key; --i) ;
        return i; // 查找成功，返回元素下标，查找失败，则返回0
    }

    public static void main(String[] args) {
        SSTable L = new SSTable(6);
//        L.elem = new int[]{-1, 0, 3, 5, 9, 12};
        L.elem = new int[]{0, -1, 0, 3, 5, 9, 12}; // 第0个位置没有值
        System.out.println(Search_Seq(L, 9));
    }

}

class SSTable {
    int[] elem; // 元素

    int TableLen; // 表的长度

    /**
     * @param len 数组length长度
     */
    public SSTable(int len) {
        TableLen = len;
        elem = new int[TableLen + 1];
    }
}
