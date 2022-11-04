package com.java.GarbageCollection.EDAP20221103;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ReferfencesTest {
    private WeakReference<Map<Integer, String>> myMap;

    private void doFunction() {
        Map<Integer, String> map = new HashMap<>();
        myMap = new WeakReference<>(map);
        int i = 0;
        while (true) {
            if (myMap != null && myMap.get() != null) {
                if (myMap.get() != null) {
                    myMap.get().put(i++, "test" + i);
                    System.out.println("i'm still working!!!" + i + " Map Size" + myMap.get().size());
                } else {
                    System.out.println("*****i'm free*****");
                    return;
                }
                System.out.println(Runtime.getRuntime().freeMemory());
                System.out.println(Runtime.getRuntime().totalMemory());
                System.out.println(Runtime.getRuntime().maxMemory());
                if (Runtime.getRuntime().freeMemory() > Runtime.getRuntime().totalMemory()) {
                    System.gc();
                }
            } else {
                System.out.println("*****i'm free*****");
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new ReferfencesTest().doFunction();
    }
}
