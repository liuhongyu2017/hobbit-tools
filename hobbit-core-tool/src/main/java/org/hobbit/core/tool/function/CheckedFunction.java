package org.hobbit.core.tool.function;

import org.springframework.lang.Nullable;

/**
 * 受检的 function
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@FunctionalInterface
public interface CheckedFunction<T, R> {

  /**
   * Run the Function
   *
   * @param t T
   * @return R R
   * @throws Throwable CheckedException
   */
  @Nullable
  R apply(@Nullable T t) throws Throwable;

}
