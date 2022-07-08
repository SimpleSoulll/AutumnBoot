package simplesoul.autumnboot.rest.common.validator.oneof;

import lombok.extern.slf4j.Slf4j;
import simplesoul.autumnboot.rest.common.validator.ConstraintsProviderCache;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * OneOf类校验器的通用实现
 *
 * @author AC
 */
@Slf4j
public abstract class AbstractOneOfValidator<S extends Annotation, T> implements ConstraintValidator<S, T> {

    /**
     * 静态约束集
     */
    protected Set<T> expectations;

    /**
     * 动态约束集Supplier
     */
    protected Supplier<Set<T>> expectationSupplier;

    /**
     * 是否动态约束集
     */
    protected boolean hasDynamicExpectations = false;

    /**
     * 提取约束集(value)
     *
     * @param constraintAnnotation 注解对象
     * @return 约束集
     */
    @SuppressWarnings("unchecked")
    private Set<T> extractExpectations(S constraintAnnotation) {
        Object value;
        try {
            value = constraintAnnotation.annotationType().getDeclaredMethod("value").invoke(constraintAnnotation);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            log.error("获取约束集失败", ex);
            return new HashSet<>();
        }
        return switch (value) {
            case int[] integers -> Arrays.stream(integers).boxed().map(v -> (T) v).collect(Collectors.toSet());
            case double[] doubles -> Arrays.stream(doubles).boxed().map(v -> (T) v).collect(Collectors.toSet());
            case long[] longs -> Arrays.stream(longs).boxed().map(v -> (T) v).collect(Collectors.toSet());
            case String[] strings -> Arrays.stream(strings).map(v -> (T) v).collect(Collectors.toSet());
            case default -> handleUnsupportedNonPrimitiveConstraints();
        };
    }

    private Set<T> handleUnsupportedNonPrimitiveConstraints() {
        log.error("OneOf不支持使用非原始类型作为约束集,建议使用In");
        return new HashSet<>();
    }

    /**
     * 获取动态约束集提供类
     *
     * @param constraintAnnotation 注解对象
     * @return 动态约束集提供类
     */
    @SuppressWarnings("unchecked")
    private Optional<Class<? extends AbstractOneOfDynamicConstraintsProvider<T>>> getDynamicConstraintsProvider(S constraintAnnotation) {
        Class<? extends AbstractOneOfDynamicConstraintsProvider<T>> provider;
        try {
            provider = (Class<? extends AbstractOneOfDynamicConstraintsProvider<T>>)
                    constraintAnnotation.annotationType().getDeclaredMethod("constraintsProvider").invoke(constraintAnnotation);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException ex) {
            log.error("反射获取动态约束集提供类失败", ex);
            return Optional.empty();
        }
        boolean hasDynamicExpectations = !provider.getCanonicalName().equals(AbstractOneOfDynamicConstraintsProvider.PROVIDER);
        return hasDynamicExpectations ? Optional.of(provider) : Optional.empty();
    }

    /**
     * 反射获取提供动态约束集的Supplier
     *
     * @param provider 提供动态约束的类
     * @return Supplier
     */
    private Supplier<Set<T>> getDynamicSupplier(Class<? extends AbstractOneOfDynamicConstraintsProvider<T>> provider) {
        try {
            var constraintsProvider = ConstraintsProviderCache.getConstraintsProvider(provider);
            return constraintsProvider::getConstraints;
        } catch (RuntimeException ex) {
            log.error("反射动态约束获取方法失败", ex);
            return HashSet::new;
        }
    }

    @Override
    public void initialize(S constraintAnnotation) {
        getDynamicConstraintsProvider(constraintAnnotation).ifPresentOrElse(provider -> {
            expectationSupplier = getDynamicSupplier(provider);
            hasDynamicExpectations = true;
        }, () -> expectations = extractExpectations(constraintAnnotation));
    }

    @Override
    public boolean isValid(T value, ConstraintValidatorContext context) {
        Set<T> currentExpectations = hasDynamicExpectations ? expectationSupplier.get() : expectations;
        boolean exists = hasDynamicExpectations ? expectationSupplier.get().contains(value) : expectations.contains(value);
        if (!exists) {
            context.disableDefaultConstraintViolation();
            String messageTemplate = String.format("%s不在{%s}中", value, String.join(",",
                    currentExpectations.stream().map(Object::toString).collect(Collectors.toSet())));
            context.buildConstraintViolationWithTemplate(messageTemplate)
                    .addConstraintViolation().buildConstraintViolationWithTemplate(messageTemplate);
            return false;
        }
        return true;
    }
}
