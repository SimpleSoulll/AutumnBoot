package simplesoul.autumnboot.rest.common.validator.oneof;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 校验对象是否在指定int数组中
 *
 * @author AC
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OneOfIntsValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
public @interface OneOfInts {

    String message() default "";

    int[] value();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
