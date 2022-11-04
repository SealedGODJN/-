package com.dataStructure.真题.考研2019;

public class 广义表_查找特定值的元素是否存在_第7题 {
    public static void main(String[] args) {

    }
}

class GeneralNode {
    /**
     * 表头 = 0 | 元素 = 1 | 子表 = 2
     */
    public int vtype;

    /**
     * 信息
     * <p>
     * 引用数
     * 元素值
     * 头指针
     */
    public Info info;

    /**
     * 尾指针
     */
    public GeneralNode tlink;

    public static boolean find(GeneralNode node, String key) {
        if (node != null) {
            if (node.vtype == 0) {
                node = node.tlink;
                find(node, key);
            } else if (node.vtype == 1 && node.info.value.equals(key)) {
                return true;
            } else if (node.vtype == 2) {
                find(node.info.hlink, key);
            }
        }
        return false;
    }
}

class Info {
    /**
     * 存放表名
     */
    public String name;

    /**
     * 存数据
     */
    public String value;

    /**
     * 存指向子表的指针
     */
    public GeneralNode hlink;
}