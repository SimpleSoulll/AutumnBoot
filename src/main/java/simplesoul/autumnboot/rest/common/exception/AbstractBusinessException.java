package simplesoul.autumnboot.rest.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义的业务异常
 * 业务异常允许将错误信息直接返回给用户
 *
 * @author AC
 */
@AllArgsConstructor
public class AbstractBusinessException extends Exception {

    /**
     * 状态码
     */
    @Getter
    private Integer status;

    /**
     * 错误消息
     */
    @Getter
    private String errMsg;
}
