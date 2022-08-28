package com.dataStructure.LinkedList;

public class Node {
    private String str;
    private Node nextNode = null;

    public Node(String str) {
        this.str = str;
    }

    public Node(String str, int value) {
        this.str = str;
    }

    public void add(Node nextNode) {//先遍历到最后一个再添加
        Node indexNode = this.nextNode;
        while (true) {
            if (indexNode.hasNext() == false) {
                break;
            }
            indexNode = indexNode.getNextNode();
        }
        indexNode.nextNode = nextNode;
    }

    public void add(Node nextNode, int index) {//方法重载,指定位点上添加元素
        if (index == 0) {
            String str_mid = this.str;
            this.str = nextNode.getStr();
            this.nextNode.setStr(str_mid);
            this.nextNode.nextNode = nextNode;
        }
        Node indexNode = this.nextNode;
        int size = 1;
        while (indexNode.hasNext() && size != index) {
            size++;//放在后面0开始
            indexNode = indexNode.getNextNode();
            System.out.println("size:" + size + ",元素：" + indexNode.getStr());
        }
        if (size < index) {
            throw new RuntimeException("添加元素索引超出范围");
        } else {
            nextNode.nextNode = indexNode.getNextNode();//先在新节点后加入
            indexNode.nextNode = nextNode;//后在前面节点加入新节点
        }
    }

    public int getSize() {
        int size = 0;
        Node indexNode = this.nextNode;
        while (true) {
            size++;
            if (!indexNode.hasNext()) {
                break;
            }
            indexNode = indexNode.getNextNode();
        }
        return size;
    }

    public Node getNextNode() {
        return this.nextNode;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean hasNext() {
        return nextNode != null;
    }

    public static void main(String[] args) {
        String[] array = {"begin", "1", "2", "3", "4", "5"};
        Node rootNode = null;
        Node indexNode = null;
        boolean flag = true;
        for (String str : array) {
            if (flag) {
                rootNode = new Node(str);
                indexNode = rootNode;
                flag = false;
            } else {
                indexNode.nextNode = new Node(str);
                indexNode = indexNode.getNextNode();
            }
        }
        rootNode.add(new Node("添加元素"), 2);
        indexNode = rootNode;
//        System.out.println(rootNode.getSize());
        while (true) {
            System.out.println(indexNode.getStr());
            if (!indexNode.hasNext()) {
                break;
            }
            indexNode = indexNode.getNextNode();
        }
    }
}