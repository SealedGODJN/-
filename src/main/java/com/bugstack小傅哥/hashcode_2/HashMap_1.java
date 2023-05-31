package com.bugstack小傅哥.hashcode_2;

import java.util.ArrayList;
import java.util.List;
import com.bugstack小傅哥.hashcode_1.HashCode1;
import com.alibaba.fastjson.JSON;

/**
 * @author hjn
 * 自制简易HashMap
 */
public class HashMap_1 {
    /**
     * 问题： 假设我们有一组7个字符串，需要存放到数组中，但要求在获取每个元素的时候时间复杂度是O(1)。
     * 也就是说你不能通过循环遍历的方式进行获取，而是要定位到数组ID直接获取相应的元素。
     *
     * 方案： 如果说我们需要通过ID从数组中获取元素，
     * 那么就需要把每个字符串都计算出一个在数组中的位置ID。
     * 字符串获取ID你能想到什么方式？
     *
     * 一个字符串最直接的获取跟数字相关的信息就是HashCode，
     * 可HashCode的取值范围太大了[-2147483648, 2147483647]，
     * 不可能直接使用。那么就需要使用HashCode与数组长度做与运算，
     * 得到一个可以在数组中出现的位置。如果说有两个元素得到同样的ID，那么这个数组ID下就存放两个字符串。
     *
     * 以上呢其实就是我们要把字符串散列到数组中的一个基本思路，接下来我们就把这个思路用代码实现出来
     * @param args
     */
    public static void main(String[] args) {
//        int i = 1;
//        boolean b = !(i++ == 3) ^ (i++ ==2) && (i++==3);
//        System.out.println(b);
//        System.out.println(i);
        List<String> strings = new ArrayList<>();
        strings.add("r1oir");
        strings.add("gr3g24");
        strings.add("o41n34r1o");
        strings.add("tq2jio3");
        strings.add("qjfii4ofq");
        strings.add("124nkk");
        strings.add("t43nf");

        // 定义要存放的数组
        String[] tab = new String[8];
        for (String s : strings) {
            int hashcode = HashCode1.hashCode(s, 31);
            hashcode = hashcode & (tab.length - 1);
            System.out.println(String.format("key值=%s Idx=%d", s, hashcode));
            if (null == tab[hashcode]) {
                tab[hashcode] = s;
            } else {
                tab[hashcode] = tab[hashcode] + "->" + s;
            }
        }

        // 输出测试结果
        System.out.println(JSON.toJSONString(tab));

//        for (String s : tab) {
//            System.out.println(s + " ");
//        }

        /**
         * 以上我们实现了一个简单的HashMap，或者说还算不上HashMap，只能算做一个散列数据存放的雏形。但这样的一个数据结构放在实际使用中，会有哪些问题呢？
         *
         * 这里所有的元素存放都需要获取一个索引位置，而如果元素的位置不够散列碰撞严重，那么就失去了散列表存放的意义，没有达到预期的性能。
         * 在获取索引ID的计算公式中，需要数组长度是2的倍数，那么怎么进行初始化这个数组大小。
         * 数组越小碰撞的越大，数组越大碰撞的越小，时间与空间如何取舍。
         * 目前存放7个元素，已经有两个位置都存放了2个字符串，那么链表越来越长怎么优化。
         * 随着元素的不断添加，数组长度不足扩容时，怎么把原有的元素，拆分到新的位置上去。
         * 以上这些问题可以归纳为；扰动函数、初始化容量、负载因子、扩容方法以及链表和红黑树转换的使用等。接下来我们会逐个问题进行分析。
         *
         * #2. 扰动函数
         */

    }

    /**
     * 在HashMap存放元素时候有这样一段代码来处理哈希值，这是java 8的散列值扰动函数，用于优化散列效果；
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        /**
         * 理论上来说字符串的hashCode是一个int类型值，那可以直接作为数组下标了，
         * 且不会出现碰撞。但是这个hashCode的取值范围是[-2147483648, 2147483647]，
         * 有将近40亿的长度，谁也不能把数组初始化的这么大，内存也是放不下的。
         *
         * 我们默认初始化的Map大小是16个长度 DEFAULT_INITIAL_CAPACITY = 1 << 4，
         * 所以获取的Hash值并不能直接作为下标使用，需要与数组长度进行取模运算得到一个下标值，也就是我们上面做的散列列子。
         *
         * 那么，hashMap源码这里不只是直接获取哈希值，还进行了一次扰动计算，(h = key.hashCode()) ^ (h >>> 16)。
         * 把哈希值右移16位，也就正好是自己长度的一半，之后与原哈希值做异或运算，这样就混合了原哈希值中的高位和低位，增大了随机性。
         * 计算方式如下图；
         */
    }
}
