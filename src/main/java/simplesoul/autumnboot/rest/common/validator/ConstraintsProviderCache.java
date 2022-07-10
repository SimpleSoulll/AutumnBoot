package simplesoul.autumnboot.rest.common.validator;

import simplesoul.autumnboot.common.reflection.CachedReflectionFactory;

/**
 * 缓存ConstraintsProvider对象
 *
 * @author AC
 */
public class ConstraintsProviderCache {

    private static final CachedReflectionFactory<AbstractConstraintsProvider<?>> PROVIDER_CACHE = new CachedReflectionFactory<>();

    @SuppressWarnings("unchecked")
    public static <T> AbstractConstraintsProvider<T> getConstraintsProvider(Class<? extends AbstractConstraintsProvider<T>> providerClass) {
        return (AbstractConstraintsProvider<T>) PROVIDER_CACHE.getInstanceOf(providerClass);
    }
}
