package simplesoul.autumnboot.rest.common.docs.errorcodes;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Set;

/**
 * 记录错误码冲突的自定义异常
 *
 * @author AC
 */
public record ErrorCodeConflict(Integer errorCode, Set<String> conflicts) {
    public String getConflictMessage() {
        return String.format("%s: %s", errorCode, String.join("、", conflicts));
    }
}
