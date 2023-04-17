package com.NPU.线程八大基础.stopThreads;

/**
 * @description 最佳实践2：在catch子语句中调用Thread.currentThread().interrupt()来恢复设置中断状态，以便于在后续的执行中，依然能够检查到刚才发生了中断
 * 回到RightWayStopThreadInProd补上interrupt，让它跳出循环
 */
public class RightWayStopThreadInProd2 implements Runnable {

    @Override
    public void run() {
        // 应该在较高层次处理Exception
        while (true) {
            System.out.println("go");

            // 发生中断
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted，程序运行结束");
                break;
            }

            reInterrupt();
        }
    }

    /**
     * 其他部门编写的接口（你在调用的时候，不了解其内部源码<br>
     * 因此，应该在调用这个函数接口的地方使用try/catch捕获exception<br>
     * 并在catch中对exception进行处理
     * <br>
     * 同时，在编写函数接口的时候，在函数体内部不应该try/catch处理异常，而是抛出exception
     */
    private void reInterrupt() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // 不能注释38行的语句，否则无法真正中断
//            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
