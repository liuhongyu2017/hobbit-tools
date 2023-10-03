package org.hobbit.core.tool.desensitize.desensitization;

/**
 * 顶级脱敏实现
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public interface Desensitization<T> {

  /**
   * 脱敏实现
   *
   * @param target 脱敏对象
   * @return 返回结果
   */
  T desensitize(T target);
}
