package org.simplesoul.autumnboot.rest.common.exception.handler;

import org.simplesoul.autumnboot.rest.common.exception.AbstractBusinessException;
import org.simplesoul.autumnboot.rest.common.response.ResponseEntity;

/**
 * 处理业务逻辑异常的拦截器
 *
 * @author AC
 * @see simplesoul.autumnboot.rest.common.exception.AbstractBusinessException
 */
class BusinessExceptionHandler {

    protected static <T extends AbstractBusinessException> ResponseEntity.Exceptional handleBusinessException(T ex) {
        return new ResponseEntity.Exceptional(ex);
    }
}
