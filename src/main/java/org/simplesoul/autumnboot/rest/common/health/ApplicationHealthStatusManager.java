package org.simplesoul.autumnboot.rest.common.health;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author AC
 */
public class ApplicationHealthStatusManager {

    private static final String STATUS_RUNNING = "RUNNING";

    private static final String DEFAULT_APPLICATION_INFO = "OK";

    private static final Set<AbstractApplicationRuntimeEvent> EVENTS = new HashSet<AbstractApplicationRuntimeEvent>();

    public static <T extends AbstractApplicationRuntimeEvent> void reportApplicationRuntimeEvent(T event) {
        synchronized (EVENTS) {
            EVENTS.add(event);
        }
    }

    public static <T extends AbstractApplicationRuntimeEvent> void clearApplicationRuntimeEvent(T event) {
        synchronized (EVENTS) {
            EVENTS.remove(event);
        }
    }

    /**
     * 获取应用状态,以等级最高的事件的状态为准
     *
     * @return 应用状态
     */
    public static String getHealthStatus() {
        synchronized (EVENTS) {
            return EVENTS.stream().max(AbstractApplicationRuntimeEvent.EVENT_COMPARATOR)
                    .map(AbstractApplicationRuntimeEvent::getHealthStatus)
                    .orElse(STATUS_RUNNING);
        }
    }

    /**
     * 获取应用状态描述
     *
     * @return 基于应用事件的状态描述
     */
    public static String getApplicationStatusInfo() {
        if (EVENTS.isEmpty()) {
            return DEFAULT_APPLICATION_INFO;
        }
        synchronized (EVENTS) {
            return EVENTS.stream().sorted()
                    .map(AbstractApplicationRuntimeEvent::getEventDescription)
                    .collect(Collectors.joining(", "));
        }
    }
}
