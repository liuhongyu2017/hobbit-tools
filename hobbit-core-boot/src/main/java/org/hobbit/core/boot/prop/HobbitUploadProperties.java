package org.hobbit.core.boot.prop;

import org.hobbit.core.tool.utils.PathUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;
import lombok.Getter;
import lombok.Setter;

/**
 * 文件上传配置
 * 
 * @author lhy
 * @version 1.0.0 2023/10/04
 */
@Getter
@Setter
@ConfigurationProperties("hobbit.upload")
public class HobbitUploadProperties {

  /**
   * 文件保存目录，默认：jar 包同级目录
   */
  @Nullable
  private String savePath = PathUtil.getJarPath();
}
