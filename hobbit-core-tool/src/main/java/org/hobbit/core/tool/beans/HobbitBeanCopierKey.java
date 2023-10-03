package org.hobbit.core.tool.beans;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * copy key
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class HobbitBeanCopierKey {

  private final Class<?> source;
  private final Class<?> target;
  private final boolean useConverter;
  private final boolean nonNull;
}
