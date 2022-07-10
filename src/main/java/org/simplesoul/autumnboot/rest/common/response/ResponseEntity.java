package org.simplesoul.autumnboot.rest.common.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.simplesoul.autumnboot.rest.common.exception.AbstractBusinessException;
import org.springframework.context.annotation.Lazy;

import java.util.concurrent.TimeUnit;

/**
 * 带响应实体的接口正常返回
 *
 * @author AC
 */
@RequiredArgsConstructor
public final class ResponseEntity<T> extends AbstractSuccessResponse {

    /**
     * 状态码
     *
     * @mock 200
     */
    @Getter
    @JsonProperty("status")
    private static final Integer STATUS = 200;

    /**
     * 响应实体
     */
    @NonNull
    @Getter
    private T data;

    /**
     * 无响应实体的接口正常返回,一般用于异步接口
     */
    public static final class Done extends AbstractSuccessResponse {
        /**
         * 状态码
         *
         * @mock 200
         */
        @Getter
        @JsonProperty("status")
        private static final Integer STATUS = 200;

        @Lazy
        @JsonIgnore
        private static final Done INSTANCE = new Done();

        public static Done getInstance() {
            return INSTANCE;
        }
    }

    /**
     * 抛出业务异常时接口的返回
     * 由于业务逻辑问题导致服务出错,接口抛出错误消息
     *
     * @see simplesoul.autumnboot.rest.common.exception.handler.BusinessExceptionHandler
     */
    public static final class Exceptional extends AbstractFailureResponse {

        /**
         * 状态码
         *
         * @mock 400
         */
        @Getter
        private final Integer status;

        /**
         * 错误消息
         *
         * @mock 业务逻辑错误
         */
        @Getter
        private final String errMsg;

        public <T extends AbstractBusinessException> Exceptional(T businessException) {
            this.status = businessException.getStatus();
            this.errMsg = businessException.getErrMsg();
        }
    }

    /**
     * 服务器内部错误,接口对外抛出错误码
     * 抛出自定义异常时接口的返回
     *
     * @see simplesoul.autumnboot.rest.common.exception.handler.CustomExceptionHandler
     */
    public static final class Failure extends AbstractFailureResponse {

        @JsonIgnore
        private static final Cache<Integer, Failure> FAILURE_CACHE = Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.HOURS)
                .maximumSize(32)
                .build();

        /**
         * 状态码
         *
         * @mock 500
         */
        @Getter
        @JsonProperty("status")
        private static final Integer STATUS = 500;

        /**
         * 错误码
         *
         * @mock 500123
         */
        @Getter
        private final Integer errorCode;

        /**
         * 错误消息
         *
         * @mock 服务内部错误
         */
        @Getter
        @JsonProperty("errMsg")
        private static final String ERR_MSG = "服务内部错误";

        private Failure(int errorCode) {
            this.errorCode = errorCode;
        }

        public static Failure getInstance(int errorCode) {
            return FAILURE_CACHE.get(errorCode, Failure::new);
        }
    }

    /**
     * 接口入参检验抛出异常时的返回
     *
     * @see simplesoul.autumnboot.rest.common.exception.handler.ValidateExceptionHandler
     */
    @RequiredArgsConstructor
    public static final class ValidateError extends AbstractFailureResponse {

        /**
         * 状态码
         *
         * @mock 400
         */
        @Getter
        @JsonProperty("status")
        private static final Integer STATUS = 400;

        /**
         * 校验错误信息
         *
         * @mock name不能为空
         */
        @Getter
        @NonNull
        private final String errMsg;
    }

    /**
     * 服务出现未知(未预见)异常时的返回
     */
    public static final class Fatal extends AbstractFailureResponse {

        /**
         * 状态码
         *
         * @mock 500
         */
        @JsonProperty("status")
        private static final Integer STATUS = 500;

        /**
         * 错误消息
         *
         * @mock 服务内部错误
         */
        @JsonProperty("errMsg")
        private static final String ERR_MSG = "服务内部错误";

        @Lazy
        @JsonIgnore
        private static final Fatal INSTANCE = new Fatal();

        public static Fatal getInstance() {
            return INSTANCE;
        }
    }
}
