package com.NPU.线程八大基础.stopThreads;

/**
 * @description run无法往外抛出checked exception，只能用try/catch
 */
public class RunThrowException {
    public void aVoid() throws Exception {
        throw new Exception();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            /**
             * 原本的run()方法没有对异常进行抛出，因此Override的方法也不能抛出
             */
            // 'run()' in 'Anonymous class derived from java.lang.Runnable' clashes
            // with 'run()' in 'java.lang.Runnable';
            // overridden method does not throw 'java.lang.Exception'
//            @Override
//            public void run() throws Exception{
//                  throw new Exception();
//            }
            // 只能try/catch，run不能throws
            @Override
            public void run() {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
