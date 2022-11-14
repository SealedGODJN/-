package com.dataStructure.真题.考研2019;

import java.util.Stack;

public class Test广义表 {

    public final int TAG_TABLE = 1;

    public final int TAG_ITEM = 0;

    private char mStartSymb = '(';

    private char mEndSymb = ')';

    private Node mGenTable;

    static class Node {

        int tag;

        Object data;

        /**
         * 指向表头
         */
        Node mPh;

        /**
         * 指向表尾
         */
        Node mPt;

        public Node(Node ph, Node pt, int tag, Object data) {

            this.mPh = ph;

            this.mPt = pt;

            this.tag = tag;

            this.data = data;

        }

    }

    public Test广义表(String genTable) {

        if (genTable == null)

            throw new NullPointerException("genTable is null");

        initTable(genTable);

    }

    public Test广义表() {

        mGenTable = new Node(null, null, TAG_TABLE, null);

    }

    public Test广义表(Test广义表 b) {

        if (b != null) {

            mGenTable = b.mGenTable;

        }

    }

    public void initTable(String genTable) {

        String ts = genTable.replaceAll("\\s", "");

        int len = ts.length();

        Stack symStack = new Stack();

        Stack nodeStack = new Stack();

        initSymbolicCharactor(ts);

        mGenTable = new Node(null, null, TAG_TABLE, null);

        Node itemNode, tableNode = mGenTable, tmpNode;

        for (int i = 0; i < len; i++) {

            if (ts.charAt(i) == mStartSymb) {

                tmpNode = new Node(null, null, TAG_TABLE, null);

                symStack.push(ts.charAt(i));

                if (symStack.size() > 1) {

                    nodeStack.push(tableNode);

                    tableNode.mPh = tmpNode;

                    tableNode = tableNode.mPh;

                } else {

                    tableNode.mPt = tmpNode;

                    tableNode = tableNode.mPt;

                }

            } else if (ts.charAt(i) == mEndSymb) {

                if (symStack.isEmpty()) {

                    throw new NullPointerException(

                            "IllegalArgumentException in constructor GeneralizedTable!...");

                }

                if (symStack.size() > 1) {

                    tableNode = (Node) nodeStack.pop();

                }

                symStack.pop();

            } else if (ts.charAt(i) == ',') {

                tableNode.mPt = new Node(null, null, TAG_TABLE, null);

                tableNode = tableNode.mPt;

            } else {

                itemNode = new Node(null, null, TAG_ITEM, ts.charAt(i));

                tableNode.mPh = itemNode;

            }

        }

        if (!symStack.isEmpty()) {

            throw new NullPointerException(

                    "IllegalArgumentException in constructor GeneralizedTable!...");

        }

    }

    public void initSymbolicCharactor(String ts) {

        mStartSymb = ts.charAt(0);

        switch (mStartSymb) {

            case '(':

                mEndSymb = ')';

                break;

            case '{':

                mEndSymb = '}';

                break;

            case '[':

                mEndSymb = ']';

                break;

            default:

                throw new IllegalArgumentException(

                        "IllegalArgumentException ---> initSymbolicCharactor");

        }

    }

    public void print() {

        print(mGenTable, 0);

    }

    private void print(Node node, int deep) {

        if (node == null) return;

        if (node.tag == 0) {
            System.out.print(node.data.toString() + "[深度:" + deep + "]");
        }

        print(node.mPh, deep + 1);

        print(node.mPt, deep);

    }

    public int depth() {

        if (mGenTable == null)

            throw new NullPointerException("Generalized Table is null !.. ---> method depth");

        return depth(mGenTable);

    }

    private int depth(Node node) {

        if (node == null || node.tag == 0) return 0;

        int depHeader = 0, depTear = 0;

        depHeader = 1 + depth(node.mPh);

        depTear = depth(node.mPt);

        return Math.max(depHeader, depTear);

    }

    public int length() {

        if (mGenTable == null || mGenTable.mPt == null) return -1;

        int len = 0;

        Node node = mGenTable;

        while (node.mPt != null) {

            node = node.mPt;

            if (node.mPh == null && node.mPt == null) break;

            len++;

        }

        return len;

    }

    public Test广义表 getHeader() {

        if (isEmpty()) return null;

        Node node = mGenTable.mPt;

        Test广义表 test = new Test广义表();

        test.mGenTable.mPt = node.mPh;

        return test;

    }

    public Test广义表 getTear() {

        if (mGenTable == null) return null;

        Node node = mGenTable.mPt;

        Test广义表 test = new Test广义表();

        test.mGenTable.mPt = node.mPt;

        return test;

    }

    public boolean isEmpty() {

        if (mGenTable == null) return true;

        Node node = mGenTable.mPt;

        return node.mPh == null || node.mPt == null;

    }

    public static void main(String[] args) {

        Test广义表 test = new Test广义表("(c,a,b,(a,b,c),(a,(a,b),c))");

        test.print();

        System.out.println();

        System.out.println("该广义表的深度为:" + test.depth());

        System.out.println("该广义表的长度为:" + test.length());

        Test广义表 t2 = test.getTear();

        t2.print();

    }

}