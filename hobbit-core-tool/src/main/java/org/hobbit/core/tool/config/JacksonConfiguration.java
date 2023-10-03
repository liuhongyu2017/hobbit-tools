package org.hobbit.core.tool.config;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;
import org.hobbit.core.tool.jackson.HobbitJacksonProperties;
import org.hobbit.core.tool.jackson.HobbitJavaTimeModule;
import org.hobbit.core.tool.utils.DateUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 * Jackson配置类
 * 
 * @author lhy
 * @version 1.0.0 2023/10/03
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@EnableConfigurationProperties(HobbitJacksonProperties.class)
public class JacksonConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    builder.simpleDateFormat(DateUtil.PATTERN_DATETIME);
    // 创建ObjectMapper
    ObjectMapper objectMapper = builder.createXmlMapper(false).build();
    // 设置地点为中国
    objectMapper.setLocale(Locale.CHINA);
    // 去掉默认的时间戳格式
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    // 设置为中国上海时区
    objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
    // 序列化时，日期的统一格式
    objectMapper.setDateFormat(new SimpleDateFormat(DateUtil.PATTERN_DATETIME, Locale.CHINA));
    // 序列化处理
    objectMapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
    objectMapper.configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER.mappedFeature(),
        true);
    // 失败处理
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    // 单引号处理
    objectMapper.configure(JsonReadFeature.ALLOW_SINGLE_QUOTES.mappedFeature(), true);
    // 日期格式化
    JsonMapper.builder(objectMapper.getFactory())
        .disable(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS);
    objectMapper.registerModule(HobbitJavaTimeModule.INSTANCE);
    JsonMapper.builder(objectMapper.getFactory())
        .enable(MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS);
    objectMapper.findAndRegisterModules();
    return objectMapper;
  }
}
