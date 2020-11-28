package org.saxing.thinking.in.spring.ioc.dependency.injection.annotation;

import java.lang.annotation.*;

/**
 * injected user
 *
 * @author saxing 2020/11/28 21:08
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectedUser {
}
