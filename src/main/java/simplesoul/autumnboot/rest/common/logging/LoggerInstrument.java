package simplesoul.autumnboot.rest.common.logging;

import ch.qos.logback.classic.Logger;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.util.HotSwapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author AC
 */
@Slf4j
public class LoggerInstrument {

    private static final String ERROR_METHOD_NAME = "error";

    private static final String WARN_METHOD_NAME = "warn";

    /**
     * @see ErrorCounter#increment()
     */
    private static final String ERROR_COUNTER_INCREMENT_METHOD = String.format("%s.%s;", ErrorCounter.class.getCanonicalName(), "increment()");

    /**
     * @see WarningCounter#increment()
     */
    private static final String WARNING_COUNTER_INCREMENT_METHOD = String.format("%s.%s;", WarningCounter.class.getCanonicalName(), "increment()");

    /**
     * 向{@link org.slf4j.Logger}实现类的所有error方法插入{@link ErrorCounter#increment()}逻辑
     * 向{@link org.slf4j.Logger}实现类的所有warn方法插入{@link WarningCounter#increment()}逻辑
     *
     * @apiNote 增加JVM选项 --add-opens java.base/java.lang=ALL-UNNAMED -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000
     */
    public static void achieveCounters() {
        var cp = ClassPool.getDefault();
        try {
            var loggerClass = cp.getCtClass(log.getClass().getCanonicalName());
            Arrays.stream(loggerClass.getDeclaredMethods(ERROR_METHOD_NAME)).forEach(errorMethod -> {
                try {
                    errorMethod.insertBefore(ERROR_COUNTER_INCREMENT_METHOD);
                } catch (CannotCompileException ex) {
                    log.error(String.format("向%s插入错误日志计数器失败", loggerClass.getName()), ex);
                }
            });
            Arrays.stream(loggerClass.getDeclaredMethods(WARN_METHOD_NAME)).forEach(warnMethod -> {
                try {
                    warnMethod.insertBefore(WARNING_COUNTER_INCREMENT_METHOD);
                } catch (CannotCompileException ex) {
                    log.error(String.format("向%s插入警告日志计数器失败", loggerClass.getName()), ex);
                }
            });
            HotSwapper swapper = new HotSwapper(8000);
            swapper.reload(Logger.class.getCanonicalName(), loggerClass.toBytecode());
        } catch (IllegalConnectorArgumentsException | NotFoundException | IOException | CannotCompileException ex) {
            log.error("插入日志监听器出错", ex);
        }
    }
}
