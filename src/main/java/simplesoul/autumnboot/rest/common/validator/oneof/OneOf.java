package simplesoul.autumnboot.rest.common.validator.oneof;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 校验对象是否在指定对象数组中
 * @author AC
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OneOfValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
public @interface OneOf {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends AbstractOneOfDynamicConstraintsProvider> constraintsProvider() default AbstractOneOfDynamicConstraintsProvider.class;
}
