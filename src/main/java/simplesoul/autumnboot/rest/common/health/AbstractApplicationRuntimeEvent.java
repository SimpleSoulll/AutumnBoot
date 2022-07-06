package simplesoul.autumnboot.rest.common.health;

import java.util.Comparator;

/**
 * 继承本抽象类定义服务运行时事件
 *
 * @author AC
 */
public abstract class AbstractApplicationRuntimeEvent {

    /**
     * 事件对服务状态的影响
     *
     * @return 触发事件后,/health返回的服务状态
     */
    abstract String getHealthStatus();

    /**
     * 获取事件描述
     *
     * @return 事件描述
     */
    abstract String getEventDescription();

    /**
     * 获取事件等级,等级越低越严重
     *
     * @return 事件等级
     */
    abstract int getEventLevel();

    public final static Comparator<AbstractApplicationRuntimeEvent> EVENT_COMPARATOR = new Comparator<AbstractApplicationRuntimeEvent>() {
        @Override
        public int compare(AbstractApplicationRuntimeEvent o1, AbstractApplicationRuntimeEvent o2) {
            return o1.getEventLevel() - o2.getEventLevel();
        }
    };

    @Override
    public int hashCode() {
        return (getEventLevel() + getHealthStatus() + getEventDescription()).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AbstractApplicationRuntimeEvent && hashCode() == obj.hashCode();
    }
}
