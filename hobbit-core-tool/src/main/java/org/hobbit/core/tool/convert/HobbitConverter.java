package org.hobbit.core.tool.convert;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.hobbit.core.tool.function.CheckedFunction;
import org.hobbit.core.tool.utils.ClassUtil;
import org.hobbit.core.tool.utils.ConvertUtil;
import org.hobbit.core.tool.utils.ReflectUtil;
import org.hobbit.core.tool.utils.Unchecked;
import org.springframework.cglib.core.Converter;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 组合 spring cglib Converter 和 spring ConversionService
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@Slf4j
@AllArgsConstructor
public class HobbitConverter implements Converter {

  private static final ConcurrentMap<String, TypeDescriptor> TYPE_CACHE = new ConcurrentHashMap<>();
  private final Class<?> sourceClazz;
  private final Class<?> targetClazz;

  /**
   * cglib convert
   *
   * @param value 源对象属性
   * @param target 目标对象属性类
   * @param fieldName 目标的field名，原为 set 方法名，HobbitBeanCopier 里做了更改
   * @return {Object}
   */
  @SuppressWarnings("rawtypes")
  @Override
  @Nullable
  public Object convert(Object value, Class target, final Object fieldName) {
    if (value == null) {
      return null;
    }
    // 类型一样，不需要转换
    if (ClassUtil.isAssignableValue(target, value)) {
      return value;
    }
    try {
      TypeDescriptor targetDescriptor =
          HobbitConverter.getTypeDescriptor(targetClazz, (String) fieldName);
      // 1. 判断 sourceClazz 为 Map
      if (Map.class.isAssignableFrom(sourceClazz)) {
        return ConvertUtil.convert(value, targetDescriptor);
      } else {
        TypeDescriptor sourceDescriptor =
            HobbitConverter.getTypeDescriptor(sourceClazz, (String) fieldName);
        return ConvertUtil.convert(value, sourceDescriptor, targetDescriptor);
      }
    } catch (Throwable e) {
      log.warn("HobbitConverter error", e);
      return null;
    }
  }

  private static TypeDescriptor getTypeDescriptor(final Class<?> clazz, final String fieldName) {
    String srcCacheKey = clazz.getName() + fieldName;
    // 忽略抛出异常的函数，定义完整泛型，避免编译问题
    CheckedFunction<String, TypeDescriptor> uncheckedFunction = (key) -> {
      // 这里 property 理论上不会为 null
      Field field = ReflectUtil.getField(clazz, fieldName);
      if (field == null) {
        throw new NoSuchFieldException(fieldName);
      }
      return new TypeDescriptor(field);
    };
    return TYPE_CACHE.computeIfAbsent(srcCacheKey, Unchecked.function(uncheckedFunction));
  }
}
