package org.hobbit.tools.utils;

import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
@Component
public class SpringUtil implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(
      @SuppressWarnings("NullableProblems") ApplicationContext applicationContext)
      throws BeansException {
    SpringUtil.applicationContext = applicationContext;
  }

  /**
   * 获取applicationContext
   *
   * @return ApplicationContext
   */
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  /**
   * 通过 name获取 Bean
   *
   * @param <T>  Bean类型
   * @param name Bean名称
   * @return Bean
   */
  @SuppressWarnings("unchecked")
  public static <T> T getBean(String name) {
    return (T) applicationContext.getBean(name);
  }

  /**
   * 通过class获取Bean
   *
   * @param <T>   Bean类型
   * @param clazz Bean类
   * @return Bean对象
   */
  public static <T> T getBean(Class<T> clazz) {
    return applicationContext.getBean(clazz);
  }

  /**
   * 通过name,以及Clazz返回指定的Bean
   *
   * @param <T>   bean类型
   * @param name  Bean名称
   * @param clazz bean类型
   * @return Bean对象
   */
  public static <T> T getBean(String name, Class<T> clazz) {
    return applicationContext.getBean(name, clazz);
  }

  /**
   * 获取指定类型对应的所有Bean，包括子类
   *
   * @param <T>  Bean类型
   * @param type 类、接口，null表示获取所有bean
   * @return 类型对应的bean，key是bean注册的name，value是Bean
   * @since 5.3.3
   */
  public static <T> Map<String, T> getBeansOfType(Class<T> type) {
    return applicationContext.getBeansOfType(type);
  }

  /**
   * 获取指定类型对应的Bean名称，包括子类
   *
   * @param type 类、接口，null表示获取所有bean名称
   * @return bean名称
   * @since 5.3.3
   */
  public static String[] getBeanNamesForType(Class<?> type) {
    return applicationContext.getBeanNamesForType(type);
  }

  /**
   * 获取配置文件配置项的值
   *
   * @param key 配置项key
   * @return 属性值
   * @since 5.3.3
   */
  public static String getProperty(String key) {
    return applicationContext.getEnvironment().getProperty(key);
  }
}
