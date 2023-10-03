package org.hobbit.core.launch.utils;

import java.util.Properties;
import org.springframework.util.StringUtils;

/**
 * 配置工具类
 * 
 * @author lhy
 * @version 1.0.0 2023/10/03
 */
public class PropsUtil {

  /**
   * 设置配置值，已存在则跳过
   *
   * @param props property
   * @param key key
   * @param value value
   */
  public static void setProperty(Properties props, String key, String value) {
    if (StringUtils.hasLength(props.getProperty(key))) {
      props.setProperty(key, value);
    }
  }
}
