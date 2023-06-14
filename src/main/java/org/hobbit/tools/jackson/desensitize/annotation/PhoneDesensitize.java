package org.hobbit.tools.jackson.desensitize.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.hobbit.tools.jackson.desensitize.desensitization.PhoneDesensitization;

/**
 * 手机号脱敏
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Desensitize(desensitization = PhoneDesensitization.class)
@Documented
public @interface PhoneDesensitize {

}
