package simplesoul.autumnboot.rest.common.health;

import java.util.Vector;
import java.util.stream.Collectors;

/**
 * @author AC
 */
public class ApplicationHealthStatusManager {

    private static final Vector<AbstractApplicationRuntimeEvent> EVENTS = new Vector<>();

    public static <T extends AbstractApplicationRuntimeEvent> void reportApplicationRuntimeEvent(T event) {
        synchronized (EVENTS) {
            EVENTS.add(event);
        }
    }

    public static String getHealthStatus() {
        synchronized (EVENTS) {
            return EVENTS.stream().max(AbstractApplicationRuntimeEvent.EVENT_COMPARATOR)
                    .map(AbstractApplicationRuntimeEvent::getHealthStatus)
                    .orElse("OK");
        }
    }
}
