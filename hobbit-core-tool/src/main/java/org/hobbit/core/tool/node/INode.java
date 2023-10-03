package org.hobbit.core.tool.node;

import java.io.Serializable;
import java.util.List;

/**
 * @author lhy
 * @version 1.0.0 2023/10/3
 */
public interface INode<T> extends Serializable {

  /**
   * 主键
   *
   * @return Long
   */
  Long getId();

  /**
   * 父主键
   *
   * @return Long
   */
  Long getParentId();

  /**
   * 子孙节点
   *
   * @return List<T>
   */
  List<T> getChildren();

  /**
   * 是否有子孙节点
   *
   * @return Boolean
   */
  default Boolean getHasChildren() {
    return this.getChildren() != null && this.getChildren().isEmpty();
  }
}
