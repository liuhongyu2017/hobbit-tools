package org.hobbit.core.tool.utils;

import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

/**
 * object 工具类，扩展 spring util 工具类
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public class ObjectUtil extends ObjectUtils {

  /**
   * 判断元素不为空
   *
   * @param obj object
   * @return boolean
   */
  public static boolean isNotEmpty(@Nullable Object obj) {
    return !ObjectUtil.isEmpty(obj);
  }
}
