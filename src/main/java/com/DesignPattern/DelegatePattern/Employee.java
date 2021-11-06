package com.DesignPattern.DelegatePattern;

public interface Employee {
    /**
     * 接收任务指令工作
     * @param command
     */
    void working(String command);
}