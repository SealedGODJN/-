package com.dataStructure.Search;

public class BinarySearch {
    public static int Binary_Search(SSTable L, int key) {
        int low = 0, high = L.TableLen - 1; // 定义 L在左闭右闭的区间里，[low, high]
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
//            mid = low + ((high - low) / 2); // 防止溢出 等同于(left + high)/2
            if (L.elem[mid] == key) {
                return mid; // 数组中找到目标值，直接返回下标
            } else if (L.elem[mid] > key) {
                high = mid - 1; //  L在左区间，所以[left, middle - 1]
            } else low = mid + 1; //  L在右区间，所以[middle + 1, high]
        }
        return -1;
    }

    public static void main(String[] args) {
        SSTable L = new SSTable(6);
//        L.elem = new int[]{-1, 0, 3, 5, 9, 12};
        L.elem = new int[]{-1, 0, 3, 5, 9, 12};
//        System.out.println(Binary_Search(L, 9));
        System.out.println(Binary_Search(L, 2));

        /**
         * 测试int取平均值
         */
//        int mid = (0 + 5) / 2;
//        System.out.println(mid);
    }
}
