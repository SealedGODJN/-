package com.DesignPattern.BuilderPattern.Impove;

/**
 * 抽象构建者类
 */
public abstract class ComputerBuilder {
    public abstract void setUsbCount();
    public abstract void setKeyboard();
    public abstract void setDisplay();

    public abstract Computer getComputer();
}
