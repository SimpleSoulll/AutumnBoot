package simplesoul.autumnboot.rest.common.validator.oneof;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * OneOf类校验器的通用实现
 *
 * @author AC
 */
@Slf4j
public abstract class AbstractOneOfValidator<S extends Annotation, T> implements ConstraintValidator<S, T> {

    private Set<T> expectations;

    /**
     * 提取有效集合(value)
     *
     * @param constraintAnnotation 注解对象
     * @return 有效集合
     */
    abstract protected Set<T> extractExpectations(S constraintAnnotation);

    @Override
    public void initialize(S constraintAnnotation) {
        expectations = extractExpectations(constraintAnnotation);
    }

    @Override
    public boolean isValid(T value, ConstraintValidatorContext context) {
        if (expectations.stream().anyMatch(value::equals)) {
            return true;
        } else {
            context.disableDefaultConstraintViolation();
            String messageTemplate = String.format("%s不在{%s}中", value, String.join(",",
                    expectations.stream().map(Object::toString).collect(Collectors.toSet())));
            context.buildConstraintViolationWithTemplate(messageTemplate)
                    .addConstraintViolation().buildConstraintViolationWithTemplate(messageTemplate);
            return false;
        }
    }
}
