package org.hobbit.core.tool.function;

import org.springframework.lang.Nullable;

/**
 * 受检的 Consumer
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@FunctionalInterface
public interface CheckedConsumer<T> {

  /**
   * Run the Consumer
   *
   * @param t T
   */
  void accept(@Nullable T t);

}
