package com.dataStructure.真题.考研2019;

public class 广义表_查找特定值的元素是否存在_第7题 {
    public static void main(String[] args) {

        GeneralList generalList = new GeneralList();

        广义表_查找特定值的元素是否存在_第7题 test = new 广义表_查找特定值的元素是否存在_第7题();
        test.createGList(generalList, "(((a,b),(c,d)),(e,(f,g),h),z)");

        System.out.println(GeneralList.find(generalList, "f"));
    }

    //建立广义表"(((a,b),(c,d)),(e,(f,g),h),z)"这样形式的字符串为内通建立广义表
    //同样采用递归方式。结束条件是空表和原子。
    //递归建立表头和表尾
    public GeneralList createGList(GeneralList L, String s) {
        System.out.println(s);
        GeneralList p = null;
        GeneralList q = null;

        if (s == null || s.equals("()")) {
            //如果是空表
            return null;
        } else {
            if (L == null) {
                L = new GeneralList();
            }
            if (s.length() == 1) {
                //创建单原子广义表
                L.vtype = 1;
                L.info.value = String.valueOf(s.charAt(0));
                L.info.hlink = null;
            } else {
                L.vtype = 2;
                p = L;
                // 去除最外层的括号 or
                String sub = s.substring(1, s.length() - 1);

                do {
                    // 从表尾中取出表头的内容，循环建立同一层次的结点
                    Temp temp = new Temp(sub);
                    String hsub = sever(temp);
                    sub = temp.string;

                    p.info.hlink = createGList(p.info.hlink, hsub);
                    q = p; // hsub是头,建立表头

                    if (!sub.isEmpty()) {//如果有尾
                        p = new GeneralList();
                        p.vtype = 2;
                        q.tlink = p;
                    }
                } while (!sub.isEmpty());
                q.tlink = null;
            }
        }

        return L;
    }

    /**
     * 把表头和表尾分开
     * 该函数处理(((a,b),(c,d)),(e,(f,g),h),z)后，hstr = ((a,b),(c,d)) str = (e,(f,g),h),z.
     *
     * @param t 广义表的字符串表示
     */
    public static String sever(Temp t) {
        String str = t.string;
        int n = str.length();
        int i = 0;
        int k = 0;
        char ch;
        String hstr = null;

        do {
            ch = str.charAt(i);
            i++;

            if (ch == '(') k++;
            else if (ch == ')') k--;
        } while (i < n && (ch != ',' || k != 0));

        if (i < n) {
            hstr = str.substring(0, i - 1);
            str = str.substring(i);
        } else {
            hstr = str;
            str = "";
        }

        t.string = str;
        return hstr;
    }

    //为了应对值传递，只能传递引用拷贝，无法传递“地址”的问题
    static class Temp {
        String string = "";

        public Temp(String s) {
            string = s;
        }
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
    public GeneralList tlink;

    /**
     * @param node
     * @param key         查找的内容
     * @return 找到，则返回true<br>
     * 没找到，则返回false<br>
     */
    public static boolean find(GeneralList node, String key) {
        boolean returnValue1 = false, returnValue2 = false, returnValue3 = false;
        if (node != null) {
            if (node.vtype == 0) {
                returnValue1 = find(node.tlink, key);
            }
            if (node.vtype == 1 && node.info.value.equals(key)) {
                return true;
            } else if (node.vtype == 2) {
                find(node.info.hlink, key);
//                find(generalList.info.hLink.tLink, key);
                find(node.tlink, key);
            }
            if (node.vtype == 2) {
                returnValue2 = find(node.info.hlink, key);
                returnValue3 = find(node.tlink, key);
            }
        }
        return returnValue1 || returnValue2 || returnValue3;
    }
}

class Info {
//    /**
//     * 存放表名
//     */
//    public String name;

    /**
     * 存数据
     */
    public String value;

    /**
     * 指向子表的表头
     */
    public GeneralList hlink;

    public Info() {
        this.hlink = null;
    }
}