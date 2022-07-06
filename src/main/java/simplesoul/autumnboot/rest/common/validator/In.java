package simplesoul.autumnboot.rest.common.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;
import java.util.Set;

/**
 * 校验
 * @author AC
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InEnumValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface In {

    String[] value();
}
