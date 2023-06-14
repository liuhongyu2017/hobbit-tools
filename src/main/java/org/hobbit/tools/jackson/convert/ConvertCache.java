package org.hobbit.tools.jackson.convert;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * 转换缓存，多例模式
 *
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
@RequiredArgsConstructor
public class ConvertCache {

  /**
   * 换来缓存转换的数据
   */
  private final Table<String, String, String> cache = HashBasedTable.create();
  private final IConvert convert;

  public String getValue(String code, String key) {
    String res;
    res = cache.get(code, key);
    if (StringUtils.isEmpty(res)) {
      Map<String, String> values = convert.getValueList(code);
      for (String item : values.keySet()) {
        String value = values.get(item);
        cache.put(code, item, value);
      }
      res = cache.get(code, key);
    }
    return res;
  }
}
