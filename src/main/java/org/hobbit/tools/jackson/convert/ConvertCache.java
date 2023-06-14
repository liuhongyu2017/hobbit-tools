package org.hobbit.tools.jackson.convert;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.hobbit.tools.utils.SpringUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 转换缓存，多例模式
 *
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
@ConditionalOnBean(IConvert.class)
@Scope(SCOPE_PROTOTYPE)
@Component
public class ConvertCache {

  /**
   * 换来缓存转换的数据
   */
  private final Table<String, String, String> cache = HashBasedTable.create();

  public String getValue(String code, String key) {
    IConvert convert = SpringUtil.getBean(IConvert.class);
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
