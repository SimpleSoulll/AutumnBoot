package simplesoul.autumnboot.rest.common.docs.errorcodes;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import simplesoul.autumnboot.rest.common.exception.AbstractCustomException;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;

/**
 * @author AC
 */
public class CustomExceptionScannerTest {

    /**
     * @see CustomExceptionScanner#getErrorCodeConflicts()
     */
    @Test
    @DisplayName("测试错误码冲突校验")
    void getErrorCodeConflictsTest() {
        var scanner = new CustomExceptionScanner(null);
        var conflicts = scanner.getErrorCodeConflicts();
        Assertions.assertIterableEquals(
                Set.of("simplesoul.autumnboot.rest.common.docs.errorcodes.CustomExceptionScannerTest.CustomException4Test",
                        "simplesoul.autumnboot.rest.service.health.HealthCheckException"),
                conflicts.stream().filter(ecf -> ecf.errorCode().equals(500123)).findFirst()
                        .map(ErrorCodeConflict::conflicts).orElse(Collections.emptySet()));
    }

    @ErrorCode(500123)
    private static class CustomException4Test extends AbstractCustomException {

        public CustomException4Test(Throwable cause) {
            super(cause);
        }
    }
}
