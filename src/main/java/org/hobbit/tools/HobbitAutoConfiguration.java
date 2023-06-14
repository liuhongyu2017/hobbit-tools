package org.hobbit.tools;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import org.hobbit.tools.jackson.convert.ConvertCache;
import org.hobbit.tools.jackson.convert.IConvert;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "SpringFacetCodeInspection"})
@AutoConfiguration
public class HobbitAutoConfiguration {

  @Scope(SCOPE_PROTOTYPE)
  @Bean
  public ConvertCache convertCache(IConvert convert) {
    return new ConvertCache(convert);
  }
}
