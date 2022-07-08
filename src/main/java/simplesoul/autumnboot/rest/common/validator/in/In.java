package simplesoul.autumnboot.rest.common.validator.in;

import simplesoul.autumnboot.rest.common.validator.AbstractConstraintsProvider;

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
@Deprecated
public @interface In {

    Class<? extends AbstractConstraintsProvider> constraintsProvider() default AbstractConstraintsProvider.class;

    Class<? extends Enum> enumsProvider() default Enum.class;
}
