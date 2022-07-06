package simplesoul.autumnboot.rest.service.health;

import org.springframework.stereotype.Service;
import simplesoul.autumnboot.rest.entity.HealthStatus;

/**
 * @author AC
 */
@Service
public class HealthCheckService {

    public HealthStatus getHealthStatus() {
        return new HealthStatus()
    }
}
