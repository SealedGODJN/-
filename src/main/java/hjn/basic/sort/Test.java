package com.hjn.basic.sort;

public class Test {
    private static int Partition(int[] arr, int start, int end) {
        int key = arr[start];
        while (start < end) {
            while (arr[end] >= key && end > start)
                end--;
            arr[start] = arr[end];
            while (arr[start] <= key && end > start)
                start++;
            arr[end] = arr[start];
        }
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
