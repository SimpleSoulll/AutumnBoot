package simplesoul.autumnboot.rest.common.logging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simplesoul.autumnboot.common.logging.ErrorCounter;
import simplesoul.autumnboot.common.logging.LoggerInstrument;
import simplesoul.autumnboot.common.logging.WarningCounter;

/**
 * @author AC
 */
public class LoggerInstrumentTest {

    /**
     * @see LoggerInstrument#achieveCounters()
     */
    @Test
    @DisplayName("测试想日志类插入计数器逻辑")
    void achieveCountersTest() {
        Logger logger = LoggerFactory.getLogger(LoggerInstrumentTest.class);
        LoggerInstrument.achieveCounters();
        logger.error("test error log counter");
        logger.warn("test warn log counter");
        Assertions.assertEquals(1, ErrorCounter.getErrors());
        Assertions.assertEquals(1, WarningCounter.getWarnings());
    }
}
