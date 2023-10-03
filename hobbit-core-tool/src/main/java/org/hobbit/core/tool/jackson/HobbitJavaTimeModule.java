package org.hobbit.core.tool.jackson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.hobbit.core.tool.utils.DateTimeUtil;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

/**
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public class HobbitJavaTimeModule extends SimpleModule {

  public static final HobbitJavaTimeModule INSTANCE = new HobbitJavaTimeModule();

  public HobbitJavaTimeModule() {
    super(PackageVersion.VERSION);
    this.addDeserializer(LocalDateTime.class,
        new LocalDateTimeDeserializer(DateTimeUtil.DATETIME_FORMAT));
    this.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeUtil.DATE_FORMAT));
    this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeUtil.TIME_FORMAT));
    this.addSerializer(LocalDateTime.class,
        new LocalDateTimeSerializer(DateTimeUtil.DATETIME_FORMAT));
    this.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeUtil.DATE_FORMAT));
    this.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeUtil.TIME_FORMAT));
  }
}
