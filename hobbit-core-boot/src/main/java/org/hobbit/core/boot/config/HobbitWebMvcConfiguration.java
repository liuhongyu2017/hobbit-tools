package org.hobbit.core.boot.config;

import java.util.List;
import org.hobbit.core.boot.prop.HobbitUploadProperties;
import org.hobbit.core.context.props.HobbitFileProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * web 配置
 * 
 * @author lhy
 * @version 1.0.0 2023/10/04
 */
@Slf4j
@AutoConfiguration
@Order(Ordered.HIGHEST_PRECEDENCE)
@AllArgsConstructor
@EnableConfigurationProperties({HobbitUploadProperties.class, HobbitFileProperties.class})
public class HobbitWebMvcConfiguration implements WebMvcConfigurer {

  private final HobbitUploadProperties hobbitUploadProperties;

  /**
   * 资源处理器
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String path = hobbitUploadProperties.getSavePath();
    registry.addResourceHandler("/upload/**").addResourceLocations("file:" + path + "/upload/");
  }

  /**
   * 参数解析器
   */
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {

  }

}
