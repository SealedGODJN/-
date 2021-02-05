package com.java.review.huawei;

public class Test {
    public static void main(String[] args) {
        // default块执行结束后，如果没有break，仍然会执行下一个case
//        switch (3){
//            default:
//                System.out.print(3);
//            case 0:
//                System.out.print(0);
//                break;
//            case 1:
//                System.out.print(1);
//        }

        // 测试int整型相除
//        System.out.print(6/10);

        // 测试switch语句的执行
            // SwitchBreak对象实例化
//            Test switchBreak = new Test();
//
//            switchBreak.choosePhoneBreak(1);

        // 测试：java的浮点数
//        if(2000000000f == 2000000045) {
//            System.out.println("true");
//        }

        long a = 1089212234l;
        float b = 1.0f;
        b = a;
        System.out.println(b);
    }

//    /**
//     * switch包含break语句的方法
//     *
//     * @param number
//     *            分支判断用的数值
//     */
//    private void choosePhoneBreak(int number) {
//        // 输出的结果值
//        String phoneBrand = "phonebrandc!";
//
//        switch (number) {
//            case 1:
//                phoneBrand = "phonebranda!";
//                System.out.println("You choose the " + phoneBrand);
//            case 2:
//                // TODO:给变量phoneBrand赋值，打印输出You choose the xx，xx指的是变量的值。
//                // 打印输出You choose the phonebrandb!
//                phoneBrand = "phonebrandb!";
//                System.out.println("You choose the" + phoneBrand);
//                break;
//            default:
//                System.out.println("You choose the " + phoneBrand);
//        }
//    }
}
