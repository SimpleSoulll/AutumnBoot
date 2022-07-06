package simplesoul.autumnboot.rest.common.validator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * 校验对象是否在指定的枚举类中
 *
 * @author AC
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InEnumValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface InEnum {

    @SuppressWarnings("rawtypes") Class<? extends Enum> value();
}
