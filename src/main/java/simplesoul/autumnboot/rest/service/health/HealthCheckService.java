package simplesoul.autumnboot.rest.service.health;

import org.springframework.stereotype.Service;
import simplesoul.autumnboot.rest.common.health.ApplicationHealthStatusManager;
import simplesoul.autumnboot.common.logging.ErrorCounter;
import simplesoul.autumnboot.common.logging.WarningCounter;
import simplesoul.autumnboot.rest.entity.HealthStatus;

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
