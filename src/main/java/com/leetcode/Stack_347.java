package com.leetcode;

import java.time.LocalDate;
import java.util.*;

public class Stack_347 {

//    static class Pair implements Comparable<Pair>{
//        Integer key;
//        Integer times;
//
//        public Pair(Integer key, Integer times) {
//            this.key = key;
//            this.times = times;
//        }
//
//        public Integer getKey() {
//            return key;
//        }
//
//        public Integer getTimes() {
//            return times;
//        }
//
//        @Override
//        public int compareTo(Pair o) {
////            return this.getTimes() > o.getTimes()? 1: 0;
//            return this.getTimes().compareTo(o.getTimes());
//        }
//    }

    public int[] topKFrequent(int[] nums, int k) {
        // key 是nums[i]，value是出现的频率
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int num : nums) {
            // 之前代码不够简洁
            map.put(num,map.getOrDefault(num,0) + 1);
        }

        // 初始化优先级队列
        // 该优先级队列是一个最小堆
        // 其队头元素是频率最小的元素
        // 队尾元素是频率最大的元素
        PriorityQueue<Integer> PQ = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        int[] result = new int[k];
        for(Integer key : map.keySet()) {
            if (!PQ.contains(key)) {
                // 向队尾添加元素
                // add()函数会调用siftUp(i,key)函数
                // 向第i个位置插入key，并维护堆的大小关系
                // 因此，插入的元素会按照其出现频率被调整
                PQ.add(key);
            }
            if (PQ.size() > k) {
                // 删除队头元素
                // 队头元素是最小频率的元素
                PQ.remove();
            }
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = PQ.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        Stack_347 stack347 = new Stack_347();
        int[] nums = {4,1,-1,2,-1,2,3};
        int k = 2;
        int[] result = stack347.topKFrequent(nums, k);
        for (int i : result) {
            System.out.print(i + " ");
        }

//        // 测试PriorityQueue
////        PriorityQueue<Employee> priorityQueue = new PriorityQueue<>();
//
//        //Comparator for name field
//        Comparator<Employee> nameSorter = Comparator.comparing(Employee::getName);
//        PriorityQueue<Employee> priorityQueue = new PriorityQueue<>( nameSorter );
//
//        priorityQueue.add(new Employee(1L, "AAA", LocalDate.now()));
//        priorityQueue.add(new Employee(4L, "CCC", LocalDate.now()));
//        priorityQueue.add(new Employee(5L, "BBB", LocalDate.now()));
//        priorityQueue.add(new Employee(2L, "FFF", LocalDate.now()));
//        priorityQueue.add(new Employee(3L, "DDD", LocalDate.now()));
//        priorityQueue.add(new Employee(6L, "EEE", LocalDate.now()));
//        while(true) {
//            Employee e = priorityQueue.poll();
//            System.out.println(e);
//            if (e == null) {
//                break;
//            }
//        }
    }

    /**
     * 测试代码
     */
//    static class Employee implements Comparable<Employee> {
//        private Long id;
//        private String name;
//        private LocalDate dob;
//        public Employee(Long id, String name, LocalDate dob) {
//            super();
//            this.id = id;
//            this.name = name;
//            this.dob = dob;
//        }
//
//        @Override
//        public int compareTo(Employee emp) {
//            return this.getId().compareTo(emp.getId());
//        }
//
//        public Long getId() {
//            return id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        @Override
//        public String toString() {
//            return "Employee [id=" + id + ", name=" + name + ", dob=" + dob + "]";
//        }
//    }
}
