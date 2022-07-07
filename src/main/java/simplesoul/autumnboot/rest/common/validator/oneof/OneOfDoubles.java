package simplesoul.autumnboot.rest.common.validator.oneof;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 校验对象是否在指定double数组中
 *
 * @author AC
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OneOfDoublesValidator.class)
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface OneOfDoubles {

    String message() default "";

    double[] value();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
