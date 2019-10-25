package org.saxing.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamValidation {

    // 是否可为空
    boolean nullAble() default true;
    // 是否可为empty 主要用于List判断
    boolean emptyAble() default true;
    // 分组校验
    Class<?>[] groups() default {};

}
