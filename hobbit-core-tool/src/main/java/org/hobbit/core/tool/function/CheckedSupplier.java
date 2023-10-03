package org.hobbit.core.tool.function;

import org.springframework.lang.Nullable;

/**
 * 受检的 Supplier
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@FunctionalInterface
public interface CheckedSupplier<T> {

  /**
   * Run the Supplier
   *
   * @return T
   */
  @Nullable
  T get() throws Throwable;

}
