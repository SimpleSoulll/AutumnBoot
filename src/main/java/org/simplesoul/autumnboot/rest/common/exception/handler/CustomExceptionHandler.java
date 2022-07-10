package org.simplesoul.autumnboot.rest.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.simplesoul.autumnboot.rest.common.exception.AbstractCustomException;
import org.simplesoul.autumnboot.rest.common.response.ResponseEntity;

import java.util.Objects;

/**
 * 处理自定义异常的拦截器
 * 打印错误日志并返回错误码
 *
 * @author AC
 * @see simplesoul.autumnboot.rest.common.exception.AbstractCustomException;
 */
@Slf4j
class CustomExceptionHandler {

    protected static <T extends AbstractCustomException> ResponseEntity.Failure handleCustomException(T customException) {
        if (Objects.nonNull(customException.getCause())) {
            log.error(customException.getMessage(), customException);
        } else {
            log.error(customException.getMessage());
        }
        return ResponseEntity.Failure.getInstance(customException.getErrorCode());
    }
}
