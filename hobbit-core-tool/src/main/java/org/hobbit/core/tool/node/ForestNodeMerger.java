package org.hobbit.core.tool.node;

import java.util.List;

/**
 * 森林节点归并类
 *
 * @author lhy
 * @version 1.0.0 2023/10/3
 */
public class ForestNodeMerger {

  /**
   * 将节点数组归并为一个森林（多棵树）（填充节点的 children 域）
   *
   * @param items 节点域
   * @return 多课树的节点集合
   */
  public static <T extends INode<T>> List<T> merge(List<T> items) {
    ForestNodeManager<T> forestNodeManager = new ForestNodeManager<>(items);
    items.forEach(forestNode -> {
      if (forestNode.getParentId() != 0) {
        INode<T> node = forestNodeManager.getTreeNodeAt(forestNode.getParentId());
        if (node != null) {
          node.getChildren().add(forestNode);
        } else {
          forestNodeManager.addParentId(forestNode.getId());
        }
      }
    });
    return forestNodeManager.getRoot();
  }
}
