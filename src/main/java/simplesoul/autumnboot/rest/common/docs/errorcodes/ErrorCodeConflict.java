package simplesoul.autumnboot.rest.common.docs.errorcodes;

import java.util.Set;

/**
 * 记录错误码冲突的自定义异常
 *
 * @author AC
 */
public record ErrorCodeConflict(Integer errorCode, Set<String> conflicts) {
}
