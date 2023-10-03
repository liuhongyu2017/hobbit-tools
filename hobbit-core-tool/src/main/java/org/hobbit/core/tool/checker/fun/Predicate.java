package org.hobbit.core.tool.checker.fun;

/**
 * 断言
 *
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
@FunctionalInterface
public interface Predicate<T> {

  /**
   * 断言操作
   *
   * @param target 判断对象
   * @return true 成功
   */
  boolean apply(T target);
}
