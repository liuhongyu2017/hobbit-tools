package org.hobbit.tools.jackson.desensitize.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.hobbit.tools.jackson.desensitize.desensitization.Desensitization;
import org.hobbit.tools.jackson.desensitize.serizlizer.ObjectDesensitizeSerializer;

/**
 * 脱敏注解
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = ObjectDesensitizeSerializer.class)
@Documented
public @interface Desensitize {

  /**
   * 脱敏实现
   */
  @SuppressWarnings("all")
  Class<? extends Desensitization<?>> desensitization();
}
