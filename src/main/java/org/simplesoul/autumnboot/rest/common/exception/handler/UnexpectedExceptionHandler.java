package org.simplesoul.autumnboot.rest.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.simplesoul.autumnboot.rest.common.response.ResponseEntity;

/**
 * 处理未知(未预见)异常
 *
 * @author AC
 */
@Slf4j
class UnexpectedExceptionHandler {

    protected static ResponseEntity.Fatal handleUnexpectedException(Throwable exception) {
        log.error("接口请求异常", exception);
        return ResponseEntity.Fatal.getInstance();
    }
}
