package org.hobbit.core.tool.desensitize.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.hobbit.core.tool.desensitize.desensitization.EmailDesensitization;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

/**
 * 邮箱脱敏
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Desensitize(desensitization = EmailDesensitization.class)
@Documented
public @interface EmailDesensitize {

}
