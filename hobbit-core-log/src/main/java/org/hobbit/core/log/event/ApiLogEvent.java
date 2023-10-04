package org.hobbit.core.log.event;

import java.util.Map;
import org.springframework.context.ApplicationEvent;

/**
 * 系统日志事件
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public class ApiLogEvent extends ApplicationEvent {

  public ApiLogEvent(Map<String, Object> source) {
    super(source);
  }
}
