package simplesoul.autumnboot.rest.service.health;

import lombok.NoArgsConstructor;
import simplesoul.autumnboot.rest.common.exception.AbstractCustomException;

/**
 * @author AC
 */
public class HealthCheckException extends AbstractCustomException {

    private static final Integer ERR_CODE = 500123;

    public HealthCheckException(Throwable cause) {
        super(ERR_CODE, cause);
    }
}
