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

class GeneralList {
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
    public GeneralList tlink;

    public GeneralList() {
        this.info = new Info();
        this.tlink = null;
    }

    public static boolean find(GeneralList node, String key) {
        boolean returnValue1 = false, returnValue2 = false, returnValue3 = false;
        if (node != null) {
            if (node.vtype == 0) {
                returnValue1 = find(node.tlink, key);
            }
            if (node.vtype == 1 && node.info.value.equals(key)) {
                return true;
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
     * 存指向子表的指针
     */
    public GeneralList hlink;

    public Info() {
        this.hlink = null;
    }
}