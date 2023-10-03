package org.hobbit.core.launch.server;

import org.hobbit.core.tool.utils.INetUtil;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import lombok.Getter;

/**
 * @author lhy
 * @version 1.0.0 2023/10/03
 */
@Getter
@AutoConfiguration
public class ServerInfo implements SmartInitializingSingleton {

  private final ServerProperties serverProperties;
  private String hostName;
  private String ip;
  private Integer port;
  private String ipWithPort;

  public ServerInfo(ServerProperties serverProperties) {
    this.serverProperties = serverProperties;
  }

  @Override
  public void afterSingletonsInstantiated() {
    this.hostName = INetUtil.getHostName();
    this.ip = INetUtil.getHostIp();
    this.port = serverProperties.getPort();
    this.ipWithPort = String.format("%s:%d", ip, port);
  }
}
