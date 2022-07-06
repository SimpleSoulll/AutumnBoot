package simplesoul.autumnboot.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import simplesoul.autumnboot.rest.common.response.ResponseEntity;
import simplesoul.autumnboot.rest.entity.HealthStatus;
import simplesoul.autumnboot.rest.service.health.HealthCheckService;

/**
 * 服务状态相关接口
 *
 * @author AC
 */
@RestController
public class ApplicationHealthController {

    private final HealthCheckService healthCheckService;

    public ApplicationHealthController(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    /**
     * 获取服务状态
     */
    @GetMapping("/health")
    public ResponseEntity<HealthStatus> getApplicationHealth() {
        return new ResponseEntity<>(healthCheckService.getHealthStatus());
    }
}
