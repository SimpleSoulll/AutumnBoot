package simplesoul.autumnboot.rest.common.validator.oneof;

import org.checkerframework.checker.units.qual.A;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 校验对象是否在指定字符串中
 *
 * @author AC
 */
public class OneOfStringValidator implements ConstraintValidator<OneOfStrings, String> {

    private Set<String> expectations;

    private boolean isCaseSensitive = true;

    @Override
    public void initialize(OneOfStrings constraintAnnotation) {
        if (constraintAnnotation.caseSensitive()) {
            expectations = Arrays.stream(constraintAnnotation.value()).collect(Collectors.toSet());
        } else {
            expectations = Arrays.stream(constraintAnnotation.value()).map(String::toLowerCase).collect(Collectors.toSet());
        }
        this.isCaseSensitive = constraintAnnotation.caseSensitive();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean exists = (isCaseSensitive && expectations.contains(value)) || (!isCaseSensitive && expectations.contains(value.toLowerCase()));
        if (!exists) {
            context.disableDefaultConstraintViolation();
            String messageTemplate = String.format("%s不在{%s}中", value, String.join(",", expectations));
            context.buildConstraintViolationWithTemplate(messageTemplate)
                    .addConstraintViolation().buildConstraintViolationWithTemplate(messageTemplate);
            return false;
        }
        return true;
    }
}
