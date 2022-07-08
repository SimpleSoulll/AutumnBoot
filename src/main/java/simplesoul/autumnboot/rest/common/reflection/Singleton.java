package simplesoul.autumnboot.rest.common.reflection;

import java.lang.annotation.*;

/**
 * 注解@Singleton的类会被{@link CachedReflectionFactory#getInstanceOf(Class)}以单例方式创建
 *
 * @author AC
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Singleton {
}
