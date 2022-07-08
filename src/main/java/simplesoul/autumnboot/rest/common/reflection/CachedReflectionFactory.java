package simplesoul.autumnboot.rest.common.reflection;

import simplesoul.autumnboot.rest.common.cache.PrimeCache;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

/**
 * @author AC
 */
public class CachedReflectionFactory<T> {

    private final PrimeCache<Class<? extends T>, T> cache;

    private final Function<Class<? extends T>, T> supplier;

    public CachedReflectionFactory() {
        try {
            supplier = tClass -> {
                try {
                    var constructor = tClass.getConstructor();
                    constructor.setAccessible(true);
                    return constructor.newInstance();
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                         IllegalAccessException ex) {
                    String tClassName = tClass.getCanonicalName();
                    throw new RuntimeException(String.format("无法初始化%s,%s必须含有无参构造器", tClassName, tClassName));
                }
            };
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("构建反射工厂失败", ex);
        }
        cache = new PrimeCache<>(supplier);
    }

    /**
     * 反射创建示例
     * {@link Singleton}注解的类将以单例方式创建
     *
     * @param tClass T的Class对象
     * @param <T>    T必须有无参构造器
     * @return T的实例
     */
    public T getInstanceOf(Class<? extends T> tClass) {
        return tClass.getAnnotation(Singleton.class) != null ? cache.getOrElseUpsert(tClass) : supplier.apply(tClass);
    }
}
