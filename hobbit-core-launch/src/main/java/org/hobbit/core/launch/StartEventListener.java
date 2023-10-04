package org.hobbit.core.launch;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 项目启动通知类
 * 
 * @author lhy
 * @version 1.0.0 2023/10/03
 */
@Slf4j
@AutoConfiguration
public class StartEventListener {

  @Async
  @Order
  @EventListener(WebServerInitializedEvent.class)
  public void afterStart(WebServerInitializedEvent event) {
    Environment environment = event.getApplicationContext().getEnvironment();
    String appName = environment.getProperty("spring.application.name").toUpperCase();
    int localPort = event.getWebServer().getPort();
    String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
    log.info("---[{}]---启动完成，当前使用的端口:[{}]，环境变量:[{}]---", appName, localPort, profile);
  }
}