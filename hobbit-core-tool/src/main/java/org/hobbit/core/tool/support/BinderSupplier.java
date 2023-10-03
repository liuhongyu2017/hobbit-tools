package org.hobbit.core.tool.support;

import java.util.function.Supplier;

/**
 * 解决 no binder available 问题
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public class BinderSupplier implements Supplier<Object> {

  @Override
  public Object get() {
    return null;
  }
}
