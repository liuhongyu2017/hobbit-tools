package org.hobbit.core.log.event;

import java.util.Map;
import org.hobbit.core.context.constant.EventConstant;
import org.hobbit.core.context.props.HobbitProperties;
import org.hobbit.core.log.model.LogApi;
import org.hobbit.core.log.service.ILogService;
import org.hobbit.core.log.utils.LogAbstractUtil;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import lombok.RequiredArgsConstructor;

/**
 * 异步监听日志事件
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@RequiredArgsConstructor
public class ApiLogListener {

  private final HobbitProperties hobbitProperties;
  private final ILogService logService;

  @SuppressWarnings("unchecked")
  @Async
  @Order
  @EventListener(ApiLogEvent.class)
  public void saveApiLog(ApiLogEvent event) {
    Map<String, Object> source = (Map<String, Object>) event.getSource();
    LogApi logApi = (LogApi) source.get(EventConstant.EVENT_LOG);
    LogAbstractUtil.addOtherInfoToLog(logApi, hobbitProperties);
    logService.saveApiLog(logApi);
  }
}
