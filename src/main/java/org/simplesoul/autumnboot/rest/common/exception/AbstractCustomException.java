package org.simplesoul.autumnboot.rest.common.exception;

import lombok.Getter;
import org.simplesoul.autumnboot.rest.common.docs.errorcodes.CustomExceptionScanner;
import org.simplesoul.autumnboot.rest.common.docs.errorcodes.ErrorCode;

import java.util.Objects;

/**
 * 自定义的服务内部异常
 * 用于追踪记录异常上下文,当异常被处理时清理上下文
 * 原则上接口只返回错误码
 * <p>
 * 错误码冲突扫描{@link CustomExceptionScanner}
 * 实现本类的异常类必须包含ERR_CODE字段确保被扫描到
 *
 * @author AC
 */
public class AbstractCustomException extends Exception {

    /**
     * 错误码
     */
    @Getter
    private final Integer errorCode = getAnnotatedErrorCode();

    public AbstractCustomException(Throwable cause) {
        super(cause);
    }

    /**
     * 获取实现类的@{@link ErrorCode}注解指定的错误码
     *
     * @return 错误码
     */
    private int getAnnotatedErrorCode() {
        var errorCodeAnnotation = getClass().getAnnotation(ErrorCode.class);
        return Objects.nonNull(errorCodeAnnotation) ? errorCodeAnnotation.value() : 0;
    }
}
