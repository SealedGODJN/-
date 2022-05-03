package com.NPU.面向对象设计.chapter3.apache.common.collections;

import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TestMap {

    public static void multiValuedMap() {
        // list实现，允许value重复
        ListValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("user", "张三");
        map.put("user", "李四");
        map.put("user", "张三");
        map.put("age", "12");
// 注意：value的泛型是String, 但是get方法返回的是List<String>
        List<String> users2 = map.get("user"); // [张三,李四,张三]

// MultiValuedMap的其他方法
        map.containsKey("user"); // true
        map.containsValue("张三"); // true
        map.containsMapping("user", "张三"); // true

        int size = map.size(); // 4

        Collection<String> ss = map.values();// [张三,李四,张三,12]
        map.remove("user"); // 清空user的所有value
// 转换为原生map
        Map<String, Collection<String>> jMap = map.asMap();

    }

    public static void main(String[] args) {

    }
}
