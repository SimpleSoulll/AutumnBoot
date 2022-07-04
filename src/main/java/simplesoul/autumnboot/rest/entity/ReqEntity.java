package simplesoul.autumnboot.rest.entity;

import java.lang.annotation.*;

/**
 * 注解了@ReqEntity对象的JSR303校验错误将由{@link simplesoul.autumnboot.rest.common.exception.handler.ValidateExceptionHandler}处理
 *
 * @author AC
 */
@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqEntity {
}
