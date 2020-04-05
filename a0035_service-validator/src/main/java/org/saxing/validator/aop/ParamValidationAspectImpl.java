package org.saxing.validator.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.saxing.validator.annotation.ParamValidation;
import org.saxing.validator.annotation.ServiceValidation;
import org.saxing.validator.exception.ServiceValidationErrorCollection;
import org.saxing.validator.exception.ServiceValidationException;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Aspect
@Component
public class ParamValidationAspectImpl {

    public static final String NULLABLE_VIOLATION_MESSAGE = "Method arguments cannot be null!";
    public static final String EMPTYABLE_VIOLATION_MESSAGE = "Method arguments list size cannot be 0!";

    public ParamValidationAspectImpl() { }

//    @Pointcut("within(@org.saxing.* *)")
//    public void beanAnnotatedWithMonitor() {}
//
//    @Pointcut("execution(* *(..))")
//    public void allMethod() {}
//
//    @Pointcut("allMethod() && beanAnnotatedWithMonitor()")
//    public void publicMethodInsideAClassMarkedWithAtMonitor() {}

//
//    @Pointcut("@annotation(serviceValidation)")
//    public void annotationPointCutDefinition(final ServiceValidation serviceValidation) { }
//

    @Before("execution(* org.saxing..*.*(..))")
    public void valid(final JoinPoint joinPoint) throws NoSuchMethodException {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        Object[] args = joinPoint.getArgs();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        Annotation[][] annotations = joinPoint.getTarget().getClass().getMethod(methodName,parameterTypes).getParameterAnnotations();

        ServiceValidationErrorCollection errors = new ServiceValidationErrorCollection();

        if (parameterTypes.length > 0 && annotations.length > 0){
            for (int i = 0; i < annotations.length; i++) {
                for (int j = 0; j < annotations[i].length; j++) {
                    if (Objects.equals(annotations[i][0].annotationType().getName(), ParamValidation.class.getName())){
                        ParamValidation validation = (ParamValidation) annotations[i][0];
                        boolean nullAble = validation.nullAble();
                        boolean emptyAble = validation.emptyAble();
                        Class<?>[] groups = validation.groups();

                        if (!nullAble){
                            if (args[i] == null){
                                errors.addError(parameterTypes[i].getSimpleName(), NULLABLE_VIOLATION_MESSAGE);
                                break;
                            }
                        }

                        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
                        Validator validator = factory.getValidator();
                        doValidate(args[i], i, validator, joinPoint, errors, groups);

                        if (args[i] instanceof List){
                            List argList = (List) args[i];
                            if (emptyAble && argList.size() == 0){
                                errors.addError(parameterTypes[i].getSimpleName(), EMPTYABLE_VIOLATION_MESSAGE);
                                break;
                            }
                            if (argList.size() > 0){
                                for (int k = 0; k < argList.size(); k++) {
                                    doValidate(argList.get(i), i, validator, joinPoint, errors, groups);
                                }
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

//    @Before("annotationPointCutDefinition(serviceValidation)")
    public void valid(final JoinPoint joinPoint, final ServiceValidation serviceValidation) {

        ServiceValidationErrorCollection errors = new ServiceValidationErrorCollection();
        Object[] args = joinPoint.getArgs();

        if (serviceValidation.nullSafe()) {
            for (int argIndex = 0; argIndex < args.length; argIndex++) {

                if (args[argIndex] == null) {
                    String parameterName = resolveParameterName(joinPoint, argIndex);
                    errors.addError(parameterName, NULLABLE_VIOLATION_MESSAGE);
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

    private void doValidate(Object arg, int argIndex, Validator validator, JoinPoint joinPoint, ServiceValidationErrorCollection errors, Class<?>... groups){
        Set<ConstraintViolation<Object>> violations = validator.validate(arg, groups);
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
