package com.NPU.线程八大基础.stopThreads;

/**
 * @description 最佳实践1：catch InterruptedException之后的优先选择：在方法签名中抛出异常
 * 在run()方法中强制try/catch
 */
public class RightWayStopThreadInProd implements Runnable {

    @Override
    public void run() {
        // 应该在较高层次处理Exception
        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("go");

            try {
                throwInMethod();
            } catch (InterruptedException e) {
                // 保存日志、停止程序
                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

    /**
     * 其他部门编写的接口（你在调用的时候，不了解其内部源码<br>
     * 因此，应该在调用这个函数接口的地方使用try/catch捕获exception<br>
     * 并在catch中对exception进行处理
     * <br>
     * 同时，在编写函数接口的时候，在函数体内部不应该try/catch处理异常，而是抛出exception
     *
     * @throws InterruptedException
     */
    private void throwInMethod() throws InterruptedException {
        // 在低级层次处理Exception
//        try {
        Thread.sleep(200);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
