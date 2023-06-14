package org.hobbit.tools.jackson.convert;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hobbit.tools.utils.SpringUtil;

/**
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
public class JsonDictConvertSerializer extends JsonSerializer<String> implements
    ContextualSerializer {

  /**
   * 字典类型
   */
  private final String code;

  private final String filedName;

  public JsonDictConvertSerializer() {
    this("", "");
  }

  private JsonDictConvertSerializer(String code, String filedName) {
    this.code = code;
    this.filedName = filedName;
  }

  @Override
  public void serialize(String value, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeString(value);
    if (StringUtils.isNotBlank(value)) {
      ConvertCache convertCache = SpringUtil.getBean(ConvertCache.class);
      jsonGenerator.writeFieldName(filedName + "Desc");
      String[] valueSplit = StringUtils.split(value, ",");

      List<String> descValues = new ArrayList<>();
      for (String s : valueSplit) {
        String cacheValue = convertCache.getValue(code, s);
        if (StringUtils.isNotBlank(cacheValue)) {
          descValues.add(cacheValue);
        }
      }
      if (descValues.isEmpty()) {
        jsonGenerator.writeString("");
      } else {
        jsonGenerator.writeString(String.join(",", descValues));
      }
    }
  }

  @Override
  public JsonSerializer<?> createContextual(SerializerProvider serializerProvider,
      BeanProperty beanProperty) throws JsonMappingException {
    JsonSerializer<?> result = serializerProvider.findNullValueSerializer(beanProperty);
    if (beanProperty != null) {
      JsonDictConvert jsonDictConvert = beanProperty.getAnnotation(JsonDictConvert.class);
      if (jsonDictConvert == null) {
        jsonDictConvert = beanProperty.getContextAnnotation(JsonDictConvert.class);
      }
      if (jsonDictConvert != null) {
        result = new JsonDictConvertSerializer(jsonDictConvert.value(), beanProperty.getName());
      } else {
        result = serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
      }
    }
    return result;
  }
}
