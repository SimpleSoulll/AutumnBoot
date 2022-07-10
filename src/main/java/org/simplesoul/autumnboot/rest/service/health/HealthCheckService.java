package org.simplesoul.autumnboot.rest.service.health;

import org.springframework.stereotype.Service;
import org.simplesoul.autumnboot.rest.common.health.ApplicationHealthStatusManager;
import org.simplesoul.autumnboot.common.logging.ErrorCounter;
import org.simplesoul.autumnboot.common.logging.WarningCounter;
import org.simplesoul.autumnboot.rest.entity.HealthStatus;

/**
 * @author AC
 */
@Service
public class HealthCheckService {

    /**
     * 获取服务状态
     *
     * @return 服务状态
     */
    public HealthStatus getHealthStatus() {
        String status = ApplicationHealthStatusManager.getHealthStatus();
        return new HealthStatus(status, ApplicationHealthStatusManager.getApplicationStatusInfo(),
                ErrorCounter.getErrors(), WarningCounter.getWarnings());
    }
}
