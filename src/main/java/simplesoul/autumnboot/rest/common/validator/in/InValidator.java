package simplesoul.autumnboot.rest.common.validator.in;

import simplesoul.autumnboot.rest.common.validator.AbstractConstraintsProvider;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 校验对象是否在指定的枚举列表内
 *
 * @author AC
 * TODO 动态生成校验器
 */
@SuppressWarnings("rawtypes")
@Deprecated
public class InValidator implements ConstraintValidator<In, Object> {

    private Class<? extends Enum> enumConstraintsProvider;

    private Class<? extends AbstractConstraintsProvider> constraintsProvider;

    private boolean hasValidEnumConstraintsProvider;

    private boolean hasValidConstraintsProvider;

    private static final Map<Class, List<Object>> ENUM_CONSTANTS = new ConcurrentHashMap<>();

    /**
     * 校验对象是否在指定的枚举列表内
     *
     * @param target                     被校验的对象
     * @param constraintValidatorContext 校验上下文
     * @return 是否通过校验
     */
    @Override
    public boolean isValid(Object target, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }

    @Override
    public void initialize(In constraintAnnotation) {
        hasValidEnumConstraintsProvider = !constraintAnnotation.enumsProvider().getCanonicalName().equals(Enum.class.getCanonicalName());
        hasValidConstraintsProvider = !constraintAnnotation.constraintsProvider().getCanonicalName().equals(AbstractConstraintsProvider.class.getCanonicalName());
        if (hasValidConstraintsProvider) {
            constraintsProvider = constraintAnnotation.constraintsProvider();
        }
        if (hasValidEnumConstraintsProvider) {
            enumConstraintsProvider = constraintAnnotation.enumsProvider();
        }
    }
}
