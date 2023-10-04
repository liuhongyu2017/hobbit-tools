package org.hobbit.core.log.service;

import org.hobbit.core.log.model.LogApi;

/**
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public interface ILogService {

  /**
   * TODO 保存日志
   */
  void saveApiLog(LogApi logApi);
}
