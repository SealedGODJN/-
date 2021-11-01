package com.DesignPattern.AbstractFactoryPattern;

/**
 * 抽象工厂模式技术要点总结
 * 1、要准确识别出当前场景是在构建哪些产品家族的系列产品对象。
 * 2、每个产品家族的产品系列都要在 AbstractFactory 接口里面定义
 */
public interface AbstractFactory {
    Computer makeComputer();
    MobilePhoto makeMobilePhoto();
}
