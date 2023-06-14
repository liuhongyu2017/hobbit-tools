package org.hobbit.tools.checker;

import org.hobbit.tools.checker.fun.Predicate;

/**
 * 条件
 *
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
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

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public ECheckType getType() {
    return type;
  }

  public void setType(ECheckType type) {
    this.type = type;
  }

  public Object getVal() {
    return val;
  }

  public void setVal(Object val) {
    this.val = val;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public Predicate<T> getResultPredicate() {
    return resultPredicate;
  }

  public void setResultPredicate(Predicate<T> resultPredicate) {
    this.resultPredicate = resultPredicate;
  }
}
