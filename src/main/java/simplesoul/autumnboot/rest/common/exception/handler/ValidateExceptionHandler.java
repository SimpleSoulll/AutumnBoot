package simplesoul.autumnboot.rest.common.exception.handler;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestController;
import simplesoul.autumnboot.rest.common.response.ResponseEntity;
import simplesoul.autumnboot.rest.entity.ReqEntity;

import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * 处理JSR303校验异常、请求参数绑定异常的拦截器
 *
 * @author AC
 * @apiNote 仅处理接口入参校验异常, 使用JSR303校验进行的其他校验将被忽略
 */
class ValidateExceptionHandler {

    /**
     * JSR303校验异常
     */
    protected static ResponseEntity.ValidateError handleValidateError(ConstraintViolationException validateError) {
        String errMsg = validateError.getConstraintViolations().stream()
                .filter(violation -> {
                    var rootBeanClass = violation.getRootBeanClass();
                    // 仅处理@ReqEntity、@RestController、@Controller注解类的校验异常
                    return rootBeanClass.isAnnotationPresent(ReqEntity.class)
                            || rootBeanClass.isAnnotationPresent(Controller.class)
                            || rootBeanClass.isAnnotationPresent(RestController.class);
                })
                .map(violation -> StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                        .map(Path.Node::getName)
                        .reduce((ignore, next) -> next)
                        .orElse("") + violation.getMessage()).collect(Collectors.joining(","));
        return new ResponseEntity.ValidateError(errMsg);
    }

    protected static ResponseEntity.ValidateError handleBindException(BindException bindException) {
        var errMsg = bindException.getFieldErrors().stream()
                .map(error -> error.getField() + error.getDefaultMessage())
                .collect(Collectors.joining(","));
        return new ResponseEntity.ValidateError(errMsg);
    }

    /**
     * RequestParam参数绑定异常
     */
    protected static ResponseEntity.ValidateError handleBindException(MissingServletRequestParameterException bindException) {
        var errMsg = bindException.getParameterName() + bindException.getMessage();
        return new ResponseEntity.ValidateError(errMsg);
    }
}
