package org.simplesoul.autumnboot.rest.service.health;

import org.simplesoul.autumnboot.rest.common.docs.errorcodes.ErrorCode;
import org.simplesoul.autumnboot.rest.common.exception.AbstractCustomException;

/**
 * 服务状态监测异常
 *
 * @author AC
 */
@ErrorCode(500123)
public class HealthCheckException extends AbstractCustomException {

    public HealthCheckException(Throwable cause) {
        super(cause);
    }
}
