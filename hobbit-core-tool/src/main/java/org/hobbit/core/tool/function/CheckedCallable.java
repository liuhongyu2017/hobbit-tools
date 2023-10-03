package org.hobbit.core.tool.function;

import org.springframework.lang.Nullable;

/**
 * 受检的 Callable
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@FunctionalInterface
public interface CheckedCallable<T> {

  /**
   * Run this callable.
   *
   * @return result
   */
  @Nullable
  T call();
}
