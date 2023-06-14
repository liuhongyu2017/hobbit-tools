package org.hobbit.tools.jackson.convert;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 根据数据字典code和id进行转换
 *
 * @author lhy
 * @version 1.0 2020/7/29
 */
@JsonSerialize(using = JsonDictConvertSerializer.class)
@JacksonAnnotationsInside
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonDictConvert {

  /**
   * 字典code
   */
  String value() default "";
}
