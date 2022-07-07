package simplesoul.autumnboot.rest.common.validator.oneof;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 校验对象是否在指定字符串中
 * 相比较于使用@Pattern{@link javax.validation.constraints.Pattern}优化了message且更加方便
 *
 * @author AC
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OneOfStringValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
public @interface OneOfStrings {

    String message() default "";

    String[] value() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 校验时是否大小写敏感
     */
    boolean caseSensitive() default true;

    Class<? extends AbstractOneOfDynamicConstraintsProvider> dynamicsProvider() default AbstractOneOfDynamicConstraintsProvider.class;
}
