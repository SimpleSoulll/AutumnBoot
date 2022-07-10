package org.simplesoul.autumnboot.rest.common.exception.handler;

import org.simplesoul.autumnboot.rest.common.exception.AbstractBusinessException;
import org.simplesoul.autumnboot.rest.common.exception.AbstractCustomException;
import org.simplesoul.autumnboot.rest.common.response.AbstractFailureResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * 异常拦截器
 *
 * @author AC
 */
@ControllerAdvice
public class GeneralExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public AbstractFailureResponse handleGeneralException(Throwable exception) {
        switch (exception) {
            case AbstractBusinessException businessException -> {
                return BusinessExceptionHandler.handleBusinessException(businessException);
            }
            case AbstractCustomException customException -> {
                return CustomExceptionHandler.handleCustomException(customException);
            }
            case ConstraintViolationException violationException -> {
                return ValidateExceptionHandler.handleValidateError(violationException);
            }
            case BindException bindException -> {
                return ValidateExceptionHandler.handleBindException(bindException);
            }
            case MissingServletRequestParameterException bindException -> {
                return ValidateExceptionHandler.handleBindException(bindException);
            }
            default -> {
                return UnexpectedExceptionHandler.handleUnexpectedException(exception);
            }
        }
    }
}
