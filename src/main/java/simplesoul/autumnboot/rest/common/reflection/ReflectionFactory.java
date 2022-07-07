package simplesoul.autumnboot.rest.common.reflection;

/**
 * @author AC
 */
public class ReflectionFactory {

    /**
     * 反射创建示例
     * {@link Singleton}注解的类将以单例方式创建
     *
     * @param tClass T的Class对象
     * @param <T>    T必须有无参构造器
     * @return T的实例
     */
    public static <T> T getInstanceOf(Class<T> tClass) {
        return null;
    }
}
