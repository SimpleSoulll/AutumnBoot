package org.simplesoul.autumnboot.rest.common.validator.oneof;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidatorContext;
import java.util.Set;

/**
 * 校验对象是否在指定字符串中
 *
 * @author AC
 */
@Slf4j
public class OneOfStringValidator extends AbstractOneOfValidator<OneOfStrings, String> {

    private boolean isCaseSensitive = true;

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(OneOfStrings constraintAnnotation) {
        this.isCaseSensitive = constraintAnnotation.caseSensitive();
        super.initialize(constraintAnnotation);
        if (!isCaseSensitive) {
            expectations = (Set<String>) expectations.stream().map(String::toLowerCase);
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return isCaseSensitive ? super.isValid(value, context) : super.isValid(value.toLowerCase(), context);
    }
}
