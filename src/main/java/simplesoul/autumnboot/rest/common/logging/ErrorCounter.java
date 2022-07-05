package simplesoul.autumnboot.rest.common.logging;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 错误日志计数器
 *
 * @author AC
 */
public final class ErrorCounter {

    /**
     * 错误日志计数器
     * {@link LoggerInstrument}向{@link ch.qos.logback.classic.Logger#error(String)}等写入错误日志的方法
     * 插入了调用{@link ErrorCounter#increment()}的逻辑
     */
    private static final AtomicLong ERRORS = new AtomicLong(0);

    /**
     * 获取通过{@link ch.qos.logback.classic.Logger}打印的错误日志数量
     */
    public static long getErrors() {
        synchronized (ERRORS) {
            return ERRORS.get();
        }
    }

    /**
     * 错误日志数量+1
     */
    public static void increment() {
        System.out.println("increment called");
        ERRORS.incrementAndGet();
    }
}
