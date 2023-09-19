package com.interview.huawei.huawei20230906.No3;

import javafx.util.Pair;

import java.util.*;

public class Main {
    /*
5
1 -1 -1
2 -1 2
3 1 3
4 4 -1
5 4 5

3
1 -1 -1
2 2 -1
3 -1 -1

3
1 1 -1
2 -1 2
3 2 3
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();

        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> nums = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            List<Integer> temp = new ArrayList<>();
            int x1 = sc.nextInt();
            int x2 = sc.nextInt();
            int x3 = sc.nextInt();
            temp.add(x1);
            temp.add(x2);
            temp.add(x3);

            // x1 默认的优先级
            map.put(x1, i);
            nums.add(temp);
        }

        // key 是 id
        // value 是 优先级
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });

        int min = -1;
        int max = total;
        for (List<Integer> num : nums) {
            int x1 = num.get(0);
            int x2 = num.get(1);
            int x3 = num.get(2);

            if (x2 == -1) {
                if (x3 == -1) {
                    // 无特别排序
                    queue.add(x1);
                } else {
                    // 队列尾部
                    map.put(x1, max + 1);
                    queue.add(x1);
                }
            } else {
                // x2 != -1
                if (x1 == x2) {
                    if (x3 == -1) {
                        // 队列头
                        map.put(x1, min - 1);
                        queue.add(x1);
                    } else {
                        // before x3
                        int cur = map.get(x3);
                        // 处理异常情况 x3是队头
                        if (cur == min) {
                            System.out.print(-1);
                            return;
                        }

                        if (map.get(x1) > cur) {
                            // 大于的时候才更新
                            if (cur - 1 < min) {
                                min = cur - 2;
                                // 取队列头部
//                                map.put(queue.getFirst(), min);
                            }
                            map.put(x1, cur - 1);
                        }

                        queue.add(x1);
                    }
                } else if (x1 == x3) {
                    // x1 != x2
                    // after x3
                    int cur = map.get(x2);
                    // 处理异常情况 x2是队尾
                    if (cur == max) {
                        System.out.print(-1);
                        return;
                    }

                    if (map.get(x1) < cur) {
                        // 小于的时候才更新
                        if (cur + 1 > max) {
                            max = cur + 2;
                            // 取队列尾部
//                            map.put(queue.getLast(), min);
                        }
                        map.put(x1, cur + 1);
                    }

                    queue.add(x1);
                }
            }
        }
        if(!queue.isEmpty()) {
            queue.add(queue.remove());
        }
        for(Integer p : queue) {
            System.out.print(p + " ");
        }
    }
}
