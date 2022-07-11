package org.simplesoul.autumnboot.common.logging;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 警告日志计数器
 * TODO 修改字节码的方式不安全
 *
 * @author AC
 */
public final class WarningCounter {

    /**
     * 警告日志计数器
     * {@link LoggerInstrument}向{@link ch.qos.logback.classic.Logger#warn(String)}等写入警告日志的方法
     * 插入了调用{@link WarnCounter#increment()}的逻辑
     */
    private static final AtomicInteger WARNINGS = new AtomicInteger(0);

    /**
     * 获取通过{@link ch.qos.logback.classic.Logger}打印的警告日志数量
     */
    public static int getWarnings() {
        synchronized (WARNINGS) {
            return WARNINGS.get();
        }
    }

    /**
     * 警告日志数量+1
     */
    public static void increment() {
        WARNINGS.incrementAndGet();
    }
}