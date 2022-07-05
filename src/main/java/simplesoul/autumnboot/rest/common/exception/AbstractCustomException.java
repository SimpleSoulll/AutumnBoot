package simplesoul.autumnboot.rest.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义的服务内部异常
 * 用于追踪记录异常上下文,当异常被处理时清理上下文
 * 原则上接口只返回错误码
 * <p>
 * 错误码冲突扫描{@link simplesoul.autumnboot.rest.common.docs.errorcodes.CustomExceptionScanner}
 * 实现本类的异常类必须包含ERR_CODE字段确保被扫描到
 *
 * @author AC
 */
@AllArgsConstructor
public class AbstractCustomException extends Exception {

    /**
     * 错误码
     */
    @Getter()
    private final Integer errorCode;

    public AbstractCustomException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }
}
