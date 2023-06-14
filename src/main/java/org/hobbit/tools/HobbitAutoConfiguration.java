package org.hobbit.tools;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

import org.hobbit.tools.jackson.convert.ConvertCache;
import org.hobbit.tools.jackson.convert.IConvert;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
@SuppressWarnings({"SpringFacetCodeInspection"})
@AutoConfiguration
public class HobbitAutoConfiguration {

  @ConditionalOnBean(IConvert.class)
  @Scope(SCOPE_REQUEST)
  @Bean
  public ConvertCache convertCache(IConvert convert) {
    return new ConvertCache(convert);
  }
}
