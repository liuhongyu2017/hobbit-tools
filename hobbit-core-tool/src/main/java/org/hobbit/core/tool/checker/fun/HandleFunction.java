package org.hobbit.core.tool.checker.fun;

import java.io.Serializable;
import org.hobbit.core.tool.checker.Condition;

/**
 * 功能处理器
 *
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
@FunctionalInterface
public interface HandleFunction extends Serializable {

  /**
   * @param target    目标对象
   * @param condition 条件
   * @return 是否通过
   */
  boolean apply(Object target, Condition<?> condition);
}
