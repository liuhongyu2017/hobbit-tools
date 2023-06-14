package org.hobbit.tools.checker.fun;

import java.io.Serializable;

/**
 * 支持序列化的 Function ，为了获取字段名字
 *
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
@FunctionalInterface
public interface SFunction<T, R> extends Serializable {

  /**
   * 执行方法
   */
  R apply(T t);
}
