package com.dataStructure.sort;

public class Test {
    private static int Partition(int[] arr, int start, int end) {
        int key = arr[start];
        while (start < end) {
            // key右边的元素，如果大于key，则留在右边
            // 如果小于key，则退出循环，与start位置的元素进行交换（因为start位置的元素是key）
            while (arr[end] >= key && end > start) end--;
            arr[start] = arr[end];

            // key左边的元素，如果小于key，则留在左边
            // 如果大于key，则与end位置的元素进行交换（因为如果start<end，还会继续一轮循环）
            while (arr[start] <= key && end > start) start++;
            arr[end] = arr[start];
        }
        // start位置的元素，左侧小于key，右侧都大于key（虽然左侧的元素不一定有序，右侧的元素也不一定有序）
        arr[start] = key;
        return start;
    }
    public static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int index = Partition(arr, start, end);
            quickSort(arr, start, index - 1);
            quickSort(arr, index + 1, end);
        }
    }
    public static void main(String[] args) {
        int[] data = new int[]{1,3,2,9,7,4,6,8,20,17,25,21};
        quickSort(data,0,data.length-1);
        for (int temp : data) {
            System.out.print(temp+"、");
        }
    }
}
