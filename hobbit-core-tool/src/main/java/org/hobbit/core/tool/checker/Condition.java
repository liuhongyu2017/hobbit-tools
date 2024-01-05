package org.hobbit.core.tool.checker;

import lombok.Getter;
import lombok.Setter;
import org.hobbit.core.tool.checker.fun.Predicate;

/**
 * 条件
 *
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
@Setter
@Getter
public class Condition<T> {

  private String field;

  private ECheckType type;

  private Object val;

  private String desc;

  private Predicate<T> resultPredicate;

  public Condition(String field, ECheckType type, Object val, String desc) {
    this.field = field;
    this.type = type;
    this.val = val;
    this.desc = desc;
  }
}
