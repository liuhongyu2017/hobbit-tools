package org.hobbit.tools.checker.handler;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.hobbit.tools.checker.CheckerException;
import org.hobbit.tools.checker.Condition;

/**
 * 条件处理器
 *
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
@SuppressWarnings({"unchecked", "all"})
public class ConditionHandler {

  public static boolean handleMatches(Object obj, Condition condition) {
    try {
      Class<?> targetClass = obj.getClass();
      Field field = targetClass.getDeclaredField(condition.getField());
      field.setAccessible(true);
      Object result = field.get(obj);

      boolean matches = Pattern.matches(String.valueOf(result), String.valueOf(condition.getVal()));
      if (!matches) {
        throwBizError(condition);
      }
    } catch (CheckerException e) {
      throw e;
    } catch (Exception e) {
      throwFieldError(condition);
    }
    return true;
  }

  public static boolean handleCustom(Object obj, Condition condition) {
    if (condition.getResultPredicate().apply(obj)) {
      throw new CheckerException(condition.getDesc());
    }
    return true;
  }

  public static boolean handleNe(Object obj, Condition condition) {
    try {
      Class<?> targetClass = obj.getClass();
      Field field = targetClass.getDeclaredField(condition.getField());
      field.setAccessible(true);
      Object result = field.get(obj);
      if (Objects.equals(result, condition.getVal())) {
        throwBizError(condition);
      }
    } catch (CheckerException e) {
      throw e;
    } catch (Exception e) {
      throwFieldError(condition);
    }
    return true;
  }

  private static void throwFieldError(Condition condition) {
    throwMsg(String.format("字段(%s)不存在", condition.getField()));
  }

  private static void throwBizError(Condition condition) {
    String msg =
        StringUtils.isEmpty(condition.getDesc()) ? String.format("字段(%s)值错误", condition.getField())
            : condition.getDesc();
    throwMsg(msg);
  }

  public static boolean handleNotNull(Object obj, Condition condition) {
    try {
      Class<?> targetClass = obj.getClass();
      Field field = targetClass.getDeclaredField(condition.getField());
      field.setAccessible(true);
      Object result = field.get(obj);
      if (Objects.isNull(result)) {
        throwBizError(condition);
      }
    } catch (CheckerException e) {
      throw e;
    } catch (Exception e) {
      throwFieldError(condition);
    }
    return true;
  }

  public static boolean handleNotNullNe(Object obj, Condition condition) {
    try {
      Class<?> targetClass = obj.getClass();
      Field field = targetClass.getDeclaredField(condition.getField());
      field.setAccessible(true);
      Object result = field.get(obj);
      if (Objects.isNull(result) || result.equals(condition.getVal())) {
        throwBizError(condition);
      }
    } catch (CheckerException e) {
      throw e;
    } catch (Exception e) {
      throwFieldError(condition);
    }
    return true;
  }

  public static boolean handleIn(Object obj, Condition condition) {

    try {
      Class<?> targetClass = obj.getClass();
      Field field = targetClass.getDeclaredField(condition.getField());
      field.setAccessible(true);
      Object result = field.get(obj);
      Object val = condition.getVal();
      if (val instanceof Collection) {
        Collection<?> temp = (Collection<?>) val;
        if (Objects.isNull(result) || !temp.contains(result)) {
          throwBizError(condition);
        }
      }
    } catch (CheckerException e) {
      throw e;
    } catch (Exception e) {
      throwFieldError(condition);
    }
    return true;

  }

  public static void throwMsg(String msg) {
    throw new CheckerException(msg);
  }
}
