package com.DesignPattern.AdapterPattern;

import java.util.Objects;

public class LogAdapter implements LogFactory {
    private NbLogger nbLogger;

    public LogAdapter(NbLogger nbLogger) {
        this.nbLogger = nbLogger;
    }

    /**
     * 覆写父类方法，感觉并不是完成适配器的工作，而是改变了原来的接口，提供了一个新的接口？
     * @param tag
     * @param message
     */
    @Override
    public void debug(String tag, String message) {
        Objects.requireNonNull(nbLogger);
        nbLogger.d(1, message);
    }
}

