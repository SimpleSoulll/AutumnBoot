package simplesoul.autumnboot.rest.common.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import simplesoul.autumnboot.rest.common.exception.AbstractBusinessException;

/**
 * 带响应实体的接口正常返回
 *
 * @author AC
 */
@RequiredArgsConstructor
public final class ResponseEntity<T> extends AbstractResponseEntity {

    /**
     * 状态码
     *
     * @mock 200
     */
    @Getter
    private final Integer status = HttpStatus.OK.value();

    /**
     * 响应实体
     */
    @NonNull
    private T data;

    /**
     * 无响应实体的接口正常返回,一般用于异步接口
     */
    public static final class Done extends AbstractResponseEntity {
        /**
         * 状态码
         *
         * @mock 200
         */
        private final Integer status = HttpStatus.OK.value();

        @Lazy
        private static final Done INSTANCE = new Done();

        public static Done getInstance() {
            return INSTANCE;
        }
    }

    /**
     * 抛出业务异常时接口的返回
     *
     * @see AbstractBusinessException
     */
    public static final class Exceptional extends AbstractResponseEntity {

    }
}
