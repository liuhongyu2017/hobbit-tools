package org.hobbit.core.tool.function;

/**
 * 受检的 Comparator
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@FunctionalInterface
public interface CheckedComparator<T> {

  /**
   * Compares its two arguments for order.
   *
   * @param o1 o1
   * @param o2 o2
   * @return int
   */
  int compare(T o1, T o2);

}
