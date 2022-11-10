package com.dataStructure.真题.考研2019;

public class 广义表_查找特定值的元素是否存在_第7题 {
    public static void main(String[] args) {

    }
}

/**
 * 头尾表示法
 */
class GeneralList1 {
    /**
     * 节点类型
     * utype = 1 元素
     * utype = 2 子表
     */
    int utype;

    /**
     * 有可能是元素，则获取value
     * 有可能是子表，则去看看head和tail两个指针
     */
    Info1 node;
}

/**
 * 头尾表示法
 */
class Info1 {
    /**
     * 如果是子表，表头
     */
    GeneralList1 head;

    /**
     * 如果是子表，表尾
     */
    GeneralList1 tail;

    /**
     * 元素的值
     */
    int value;
}

/**
 * 孩子兄弟表示法
 */
class GeneralList2 {
    /**
     * 节点类型
     * utype = 1 元素
     * utype = 2 子表
     */
    int utype;

    /**
     * 当前表的下一个节点【兄弟】
     */
    GeneralList2 next;

    /**
     * 【孩子】
     * 如果当前节点是子表，则child.head!=null
     * 如果当前节点不是子表，则child.head==null
     */
    Info2 child;
}

/**
 * 孩子兄弟表示法
 */
class Info2 {
    /**
     * 如果是子表，表头
     */
    GeneralList2 head;

    /**
     * 元素的值
     */
    int value;
}

/**
 * ⒈头尾表示法
 * 若广义表不空，则可分解成表头和表尾；反之，一对确定的表头和表尾可惟一地确定一个广义表。头尾表示法就是根据这一性质设计而成的一种存储方法。
 * <p>
 * 在表结点中应该包括一个指向表头的指针和指向表尾的指针；而在元素结点中应该包括所表示单元素的元素值。
 * 为了区分这两类结点，在结点中还要设置一个标志域，如果标志为2，则表示该结点为 子表 ；如果标志为1，则表示该结点为 元素结点 。
 * <p>
 * ⒉孩子兄弟表示法
 * <p>
 * 广义表的另一种表示法称为孩子兄弟表示法。
 * 在孩子兄弟表示法中，也有两种结点形式：一种是有孩子结点，用以表示列表；另一种是无孩子结点，用以表示单元素。
 * <p>
 * 在有孩子结点中包括一个指向第一个孩子（长子）的指针和一个指向兄弟的指针；而在无孩子结点中包括一个指向兄弟的指针和该元素的元素值。
 * 为了能区分这两类结点，在结点中还要设置一个标志域。如果标志为1，则表示该结点为有孩子结点；如果标志为0，则表示该结点为无孩子结点
 */
class GeneralList {
    /**
     * 表头 = 0 | 元素 = 1 | 子表 = 2
     * <p>
     * 表头是元素，表尾是广义表。
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
     * 指向表尾
     */
    public GeneralList tLink;

    public GeneralList() {
        this.info = new Info();
    }

    /**
     * @param generalList
     * @param key         查找的内容
     * @return 找到，则返回true<br>
     * 没找到，则返回false<br>
     */
    public static boolean find(GeneralList generalList, String key) {
        if (generalList != null) {
            // 表头
            if (generalList.vtype == 0) {
                find(generalList.tLink, key);
            } else if (generalList.vtype == 1 && generalList.info.value.equals(key)) {
                return true;
            } else if (generalList.vtype == 2) {
                find(generalList.info.hLink, key);
//                find(generalList.info.hLink.tLink, key);
                find(generalList.tLink, key);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        GeneralList generalList = new GeneralList();

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
     * 指向子表的表头
     */
    public GeneralList hLink;

    public Info() {

    }
}