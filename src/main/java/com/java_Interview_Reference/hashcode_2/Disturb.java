package com.java_Interview_Reference.hashcode_2;

public class Disturb {

    /**
     * disturbHashIdx 扰动函数下，下标值计算
     * @param key
     * @param size
     * @return
     */
    public static int disturbHashIdx(String key, int size) {
        return (size - 1) & (key.hashCode() ^ (key.hashCode() >>> 16));
    }

    /**
     * hashIdx 非扰动函数下，下标值计算
     * @param key
     * @param size
     * @return
     */
    public static int hashIdx(String key, int size) {
        return (size - 1) & key.hashCode();
    }

}