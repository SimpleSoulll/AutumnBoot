package simplesoul.autumnboot.rest.common.validator.in;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * 校验对象是否在特定对象集合中
 *
 * @author AC
 * TODO 避免使用Enum
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InValidator.class)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface In {

    @SuppressWarnings("rawtypes") Class<? extends Enum> value();
}
