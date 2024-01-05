package org.hobbit.core.tool.node;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hobbit.core.tool.pool.StringPool;

/**
 * 森林管理类
 *
 * @author lhy
 * @version 1.0.0 2023/10/3
 */
public class ForestNodeManager<T extends INode<T>> {

  /**
   * 森林的所有节点
   */
  private final ImmutableMap<Long, T> nodeMap;

  /**
   * 森林的父节点ID
   */
  private final Map<Long, Object> parentIdMap = Maps.newHashMap();

  public ForestNodeManager(List<T> nodes) {
    this.nodeMap = Maps.uniqueIndex(nodes, INode::getId);
  }

  /**
   * 根据节点 ID 获取一个节点
   *
   * @param id 节点 ID
   * @return 对应节点对象
   */
  public INode<T> getTreeNodeAt(Long id) {
    if (nodeMap.containsKey(id)) {
      return nodeMap.get(id);
    }
    return null;
  }

  /**
   * 增加父节点 ID
   *
   * @param parentId 父节点 ID
   */
  public void addParentId(Long parentId) {
    parentIdMap.put(parentId, StringPool.EMPTY);
  }

  /**
   * 获取树的根节点（一个森林对应多颗树）
   *
   * @return 树的根节点集合
   */
  public List<T> getRoot() {
    List<T> roots = new ArrayList<>();
    nodeMap.forEach((key, node) -> {
      if (node.getParentId() == 0 || parentIdMap.containsKey(node.getId())) {
        roots.add(node);
      }
    });
    return roots;
  }
}
