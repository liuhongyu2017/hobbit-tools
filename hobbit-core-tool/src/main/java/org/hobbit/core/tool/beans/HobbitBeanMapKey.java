package org.hobbit.core.tool.beans;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * bean map key，提高性能
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@EqualsAndHashCode
@AllArgsConstructor
public class HobbitBeanMapKey {

  private final Class<?> type;
  private final int require;
}
