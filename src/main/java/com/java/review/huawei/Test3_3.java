package com.java.review.huawei;

public class Test3_3 {
    public static void main(String[] args) {
        try{
            TryTest tryTest = new TryTest();
            System.out.println(tryTest.test());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class TryTest{
    public boolean test() throws Exception {
        try {
            throw new Exception("Something error"); // 1.抛出异常
        } catch (Exception e) { // 2.捕获的异常匹配（声明类或其父类），进入控制块
            throw e; // 3.throw前控制转移到finally块，执行完后再返回
        } finally {
//            return true; // 控制转移，直接返回，不再返回catch块，吃掉了异常
            throw new Exception("testtest");
        }

    }
}