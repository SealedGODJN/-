package com.nowcoder.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.SortedMap;
import java.util.TreeMap;

public class HJ8 {
    public static void test1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        SortedMap<Integer, Integer> result = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] lines = line.split(" ");
            int index = Integer.parseInt(lines[0]);
            int value = Integer.parseInt(lines[1]);
            if (result.getOrDefault(index, 0) == 0) {
                result.put(index, value);
            } else {
                result.put(index, result.get(index) + value);
            }
        }

        for (Integer key : result.keySet()) {
            System.out.println(key + " " + result.get(key));
        }
    }

    public static void main(String[] args) throws IOException {
        test1();
    }

    /*
148
129 85580
116 50881
117 27393
100 32215
6 88835
88 40361
57 78653
28 59528
41 41907
46 88880
53 64598
5 19381
45 27819
143 370
103 75359
10 8701
43 32347
3 84515
134 11988
65 89941
138 60355
65 16308
120 857
98 62578
22 62628
40 24666
118 23155
0 11817
13 36474
10 55782
4 77316
91 19024
80 72421
28 36353
8 88937
36 26142
51 46105
116 12428
109 31227
36 88168
135 58543
94 42200
112 54610
66 59272
0 41231
90 88635
147 40695
56 4994
64 62797
129 92991
83 5694
132 30492
115 69553
58 70010
34 66064
113 6516
111 69569
56 30166
131 38872
10 14671
24 3464
113 18983
77 15226
85 74879
126 53743
112 30751
49 11189
4 19369
35 74225
35 45722
94 51260
125 67595
56 17781
147 89199
9 91999
57 2594
15 42814
146 75003
60 79814
115 27794
105 20071
8 72038
112 7831
50 90288
27 80166
30 58519
120 33213
94 21512
22 77163
89 20689
106 7601
83 43666
90 20490
6 18760
115 39610
132 57359
133 22082
55 86957
128 56323
121 84561
121 33028
52 50357
55 65649
17 55645
104 87925
135 80568
105 68936
126 64270
101 12090
58 45644
31 62144
71 91373
90 65686
22 33562
102 81895
68 60578
97 26097
13 22247
26 4823
83 23024
44 72254
44 59718
90 81011
41 21065
118 4218
41 78235
63 4698
141 29922
106 222
95 58962
70 28187
116 34961
120 52587
108 74238
84 1943
138 46424
68 64937
109 52784
130 27464
121 29581
140 46817
103 46954
7 69852
18 68899
10 82159
68 10725
64 45113
91 64159
     */
}
