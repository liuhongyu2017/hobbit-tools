package org.hobbit.core.boot.config;

import org.hobbit.core.boot.request.HobbitRequestFilter;
import org.hobbit.core.context.props.RequestProperties;
import org.hobbit.core.context.props.XssProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import jakarta.servlet.DispatcherType;
import lombok.AllArgsConstructor;

/**
 * 过滤器配置类
 * 
 * @author lhy
 * @version 1.0.0 2023/10/04
 */
@AutoConfiguration
@AllArgsConstructor
@EnableConfigurationProperties({RequestProperties.class, XssProperties.class})
public class RequestFilterConfiguration {

  private final RequestProperties requestProperties;
  private final XssProperties xssProperties;

  /**
   * 全局过滤器
   */
  @Bean
  public FilterRegistrationBean<HobbitRequestFilter> hobbitFilterRegistration() {
    FilterRegistrationBean<HobbitRequestFilter> registration = new FilterRegistrationBean<>();
    registration.setDispatcherTypes(DispatcherType.REQUEST);
    registration.setFilter(new HobbitRequestFilter(requestProperties, xssProperties));
    registration.addUrlPatterns("/*");
    registration.setName("hobbitRequestFilter");
    registration.setOrder(Ordered.LOWEST_PRECEDENCE);
    return registration;
  }
}
