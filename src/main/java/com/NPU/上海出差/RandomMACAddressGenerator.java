package com.NPU.上海出差;

import java.io.IOException;

public class RandomMACAddressGenerator {
    public static String randomMACAddress() {
        return "FEFCFE7B06EB";
    }

    public static void changeMAC(String newMACAddress) throws IOException {
        String cmd = "reg add HKEY_LOCAL_MACHINE\\SYSTEM\\CurrentControlSet\\Control\\Class\\{4d36e972-e325-11ce-bfc1-08002be10318}\\0002 /v NetworkAddress /t REG_SZ /d "
                + newMACAddress
                + " /f";
        Process process = Runtime.getRuntime().exec(cmd);
//        log(process);// 输出命令行运行输出
        System.out.println("成功改变MAC为：" + newMACAddress);
    }

    public static void rebootNetworkCard() throws IOException {
        String disabledCmd = "netsh interface set interface \"WLAN\" disabled";
        String enabledCmd = "netsh interface set interface \"WLAN\" enabled";
        final Process disabledProcess = Runtime.getRuntime().exec(disabledCmd);
//        log(disabledProcess);// 打印命令行运行输出到控制台
        final Process enabledProcess = Runtime.getRuntime().exec(enabledCmd);
//        log(enabledProcess);// 打印命令行运行输出到控制台
    }

//    public static void log(String logMessage) {
//        System.out.println(logMessage);
//    }

    public static void main(String[] args) throws IOException {
        changeMAC(randomMACAddress());
        rebootNetworkCard();
    }
}
