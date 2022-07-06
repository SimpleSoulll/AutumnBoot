package simplesoul.autumnboot.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author AC
 */
@AllArgsConstructor
@Getter
public class HealthStatus {

    /**
     * 服务状态|RUNNING
     */
    private String state;

    /**
     * 服务描述|OK
     */
    private String info;

    /**
     * 服务错误日志数量|0
     */
    private Integer errors;

    /**
     * 服务警告日志数据量|0
     */
    private Integer warnings;
}
