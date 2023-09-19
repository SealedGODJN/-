package com.bugstack小傅哥.hashcode_2;

import net.sf.cglib.beans.BeanMap;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 避免每次进行BeanMap map = BeanMap.create();创建对象，不同于BeanCopier对象，
 * BeanMap主要针对对象实例进行处理，所以一般建议是map.setBean(pojo);进行动态替换持有的对象实例。
 * 应用场景：针对put,putAll操作会直接修改pojo对象里的属性，
 * 所以可以通过beanMap.putAll(map)进行map<->pojo属性的拷贝。
 *
 * @author lenovo
 */
public class BeanMapTest {
    /**
     * 将对象装换为map
     * @param bean 待转换的对象
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map,T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 将List<T>转换为List<Map<String, Object>>
     * @param objList
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0,size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     * @param maps
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps,Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = new ArrayList<>();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0,size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }

    public static void main(String[] args) {
//        // 初始化
////        BeanMap map = BeanMap.create(new Pojo());
//        // 构造
//        Pojo pojo = new Pojo(1, new BigInteger("2"));
////        pojo.setIntValue(1);
////        pojo.setBigInteger(new BigInteger("2"));
//
//        BeanMap map = BeanMap.create(pojo);
//        // 赋值
//        map.setBean(pojo);
//        // 验证
//        System.out.println(map.get("intValue"));
//        System.out.println(map.keySet());
//        System.out.println(map.values());

        Pojo pojo = new Pojo(2, new BigInteger("3"));
        Map<String, Object> map = beanToMap(pojo);
        for (String key : map.keySet()) {
            System.out.println(map.get(key));
        }
    }
}

class Pojo {

    private int        intValue;
    private BigInteger bigInteger;

    public Pojo(int intValue, BigInteger bigInteger) {
        this.intValue = intValue;
        this.bigInteger = bigInteger;
    }

    public Pojo() {

    }

    public void setIntValue(int i) {
        this.intValue = i;
    }

    public void setBigInteger(BigInteger bigInteger) {
        this.bigInteger = bigInteger;
    }
}