package org.hobbit.core.context.props;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Request 配置类
 *
 * @author lhy
 * @version 1.0.0 2023/10/04
 */
@Data
@ConfigurationProperties("hobbit.request")
public class RequestProperties {

  /**
   * 开启自定义request
   */
  private Boolean enabled = true;

  /**
   * 放行url
   */
  private List<String> skipUrl = new ArrayList<>();
}
