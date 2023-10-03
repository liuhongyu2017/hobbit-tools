package org.hobbit.core.tool.config;

import java.nio.charset.StandardCharsets;
import java.util.List;
import org.hobbit.core.tool.jackson.HobbitJacksonProperties;
import org.hobbit.core.tool.jackson.MappingApiJackson2HttpMessageConverter;
import org.hobbit.core.tool.utils.DateUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.ResourceRegionHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

/**
 * 消息配置类
 * 
 * @author lhy
 * @version 1.0.0 2023/10/03
 */
@Configuration
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MessageConfiguration implements WebMvcConfigurer {

  private final ObjectMapper objectMapper;
  private final HobbitJacksonProperties properties;

  /**
   * 使用 JACKSON 作为JSON MessageConverter 消息转换，内置断点续传，下载和字符串
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.removeIf(x -> x instanceof StringHttpMessageConverter
        || x instanceof AbstractJackson2HttpMessageConverter);
    converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
    converters.add(new ByteArrayHttpMessageConverter());
    converters.add(new ResourceHttpMessageConverter());
    converters.add(new ResourceRegionHttpMessageConverter());
    converters.add(new MappingApiJackson2HttpMessageConverter(objectMapper, properties));
  }

  /**
   * 日期格式化
   */
  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addFormatter(new DateFormatter(DateUtil.PATTERN_DATE));
    registry.addFormatter(new DateFormatter(DateUtil.PATTERN_DATETIME));
  }
}
