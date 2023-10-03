package org.hobbit.core.tool.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Bean属性
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@Getter
@AllArgsConstructor
public class BeanProperty {

  private final String name;
  private final Class<?> type;
}
