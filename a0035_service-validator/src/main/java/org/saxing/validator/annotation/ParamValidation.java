package org.saxing.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamValidation {

    boolean nullSafe() default true;
    boolean javaxValidation() default true;
    Class<?>[] groups() default {};

}
