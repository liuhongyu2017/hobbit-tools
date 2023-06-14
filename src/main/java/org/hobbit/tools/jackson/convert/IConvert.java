package org.hobbit.tools.jackson.convert;

import java.util.Map;

/**
 * 获取转换值
 *
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
public interface IConvert {

  /**
   * 根据code获取
   */
  Map<String, String> getValueList(String code);
}
