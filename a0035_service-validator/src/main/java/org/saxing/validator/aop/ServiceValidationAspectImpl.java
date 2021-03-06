package org.saxing.validator.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.saxing.validator.annotation.ServiceValidation;
import org.saxing.validator.exception.ServiceValidationErrorCollection;
import org.saxing.validator.exception.ServiceValidationException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Set;

@Aspect
@Component
public class ServiceValidationAspectImpl {

    public static final String NULLSAFE_VIOLATION_MESSAGE = "Method arguments cannot be null!";

    public ServiceValidationAspectImpl() { }

    @Pointcut("@annotation(serviceValidation)")
    public void annotationPointCutDefinition(final ServiceValidation serviceValidation) { }

    @Before("annotationPointCutDefinition(serviceValidation)")
    public void valid(final JoinPoint joinPoint, final ServiceValidation serviceValidation) {

        ServiceValidationErrorCollection errors = new ServiceValidationErrorCollection();
        Object[] args = joinPoint.getArgs();

        if (serviceValidation.nullSafe()) {
            for (int argIndex = 0; argIndex < args.length; argIndex++) {

                if (args[argIndex] == null) {
                    String parameterName = resolveParameterName(joinPoint, argIndex);
                    errors.addError(parameterName, NULLSAFE_VIOLATION_MESSAGE);
                }
            }
        }

        if (serviceValidation.javaxValidation()) {
            for (int argIndex = 0; argIndex < args.length; argIndex++) {
                ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                Validator validator = factory.getValidator();
                Object arg = args[argIndex];
                if (arg != null) {
                    doValidate(arg, argIndex, validator, joinPoint, errors);
                    if (arg instanceof List){
                        List argList = (List) arg;
                        if (argList.size() > 0){
                            for (int i = 0; i < argList.size(); i++) {
                                doValidate(argList.get(i), i, validator, joinPoint, errors);
                            }
                        }
                    }
                }
            }
        }

        if (!errors.isEmpty()) {
            throw new ServiceValidationException(errors);
        }
    }

    private void doValidate(Object arg, int argIndex, Validator validator, JoinPoint joinPoint, ServiceValidationErrorCollection errors){
        Set<ConstraintViolation<Object>> violations = validator.validate(arg);
        if (!violations.isEmpty()) {
            String parameterName = resolveParameterName(joinPoint, argIndex);
            for (ConstraintViolation violation : violations) {
                String valuePath = String.format("%s.%s", parameterName, violation.getPropertyPath());
                errors.addError(valuePath, violation.getMessage());
            }
        }
    }

    private String resolveParameterName(final JoinPoint joinPoint, final int argIndex) {
        if (!(joinPoint.getSignature() instanceof MethodSignature)) {
            return String.format("args[%s]", argIndex);
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        return parameters[argIndex].getType().getSimpleName();
    }
}
