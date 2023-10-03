package org.hobbit.core.tool.node;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 深林节点类
 *
 * @author lhy
 * @version 1.0.0 2023/10/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ForestNode extends BaseNode<ForestNode> {

  /**
   * 节点内容
   */
  private Object content;

  public ForestNode(Long id, Long parentId, Object content) {
    this.id = id;
    this.parentId = parentId;
    this.content = content;
  }
}
