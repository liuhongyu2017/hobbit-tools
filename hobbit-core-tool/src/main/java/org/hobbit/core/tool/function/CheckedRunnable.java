package org.hobbit.core.tool.function;

/**
 * 受检的 runnable
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@FunctionalInterface
public interface CheckedRunnable {

  /**
   * Run this runnable.
   */
  void run();

}
