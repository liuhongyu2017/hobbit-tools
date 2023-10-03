package org.hobbit.core.tool.node;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author lhy
 * @version 1.0.0 2023/10/3
 */
@Data
public class BaseNode<T> implements INode<T> {

  /**
   * 主键
   */
  @JsonSerialize(using = ToStringSerializer.class)
  protected Long id;

  /**
   * 父节点ID
   */
  @JsonSerialize(using = ToStringSerializer.class)
  protected Long parentId;

  /**
   * 子孙节点
   */
  @JsonInclude(Include.NON_EMPTY)
  protected List<T> children = new ArrayList<>();

  /**
   * 是否有子孙节点
   */
  @JsonInclude(Include.NON_EMPTY)
  private Boolean hasChildren;
}
