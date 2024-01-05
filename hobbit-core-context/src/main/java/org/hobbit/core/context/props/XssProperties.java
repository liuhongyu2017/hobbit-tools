package org.hobbit.core.context.props;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Xss 配置类
 *
 * @author lhy
 * @version 1.0.0 2023/10/04
 */
@Data
@ConfigurationProperties("hobbit.xss")
public class XssProperties {

  /**
   * 开启xss
   */
  private Boolean enabled = true;

  /**
   * 放行url
   */
  private List<String> skipUrl = new ArrayList<>();
}
