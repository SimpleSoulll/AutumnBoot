package simplesoul.autumnboot.rest.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义的服务内部异常
 * 用于追踪记录异常上下文,当异常被处理时清理上下文
 * 原则上接口只返回错误码
 *
 * @author AC
 * TODO 扫描错误码冲突
 */
@AllArgsConstructor
public class AbstractCustomException extends Exception {

    /**
     * 错误码
     */
    @Getter
    private final Integer errorCode;

    public AbstractCustomException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }
}
