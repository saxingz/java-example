package org.saxing.validator.mvc;

import org.saxing.validator.exception.ServiceValidationErrorCollection;
import org.saxing.validator.exception.ServiceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ServiceValidatorExceptionHandlerController {

    @ExceptionHandler(ServiceValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ServiceValidationErrorCollection handleException(final ServiceValidationException ex) {
        return ex.getErrors();
    }

}
