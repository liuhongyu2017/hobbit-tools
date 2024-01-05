package org.hobbit.core.log.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.hobbit.core.context.props.HobbitProperties;
import org.hobbit.core.log.model.LogAbstract;
import org.hobbit.core.tool.pool.StringPool;
import org.hobbit.core.tool.utils.DateUtil;
import org.hobbit.core.tool.utils.INetUtil;
import org.hobbit.core.tool.utils.ObjectUtil;
import org.hobbit.core.tool.utils.UrlUtil;
import org.hobbit.core.tool.utils.WebUtil;

/**
 * Log 相关工具
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public class LogAbstractUtil {

  /**
   * 向log中添加补齐request的信息
   *
   * @param request     请求
   * @param logAbstract 日志基础类
   */
  public static void addRequestInfoToLog(HttpServletRequest request, LogAbstract logAbstract) {
    if (ObjectUtil.isNotEmpty(request)) {
      logAbstract.setRemoteIp(WebUtil.getIP(request));
      logAbstract.setUserAgent(request.getHeader(WebUtil.USER_AGENT_HEADER));
      logAbstract.setRequestUri(UrlUtil.getPath(request.getRequestURI()));
      logAbstract.setMethod(request.getMethod());
      logAbstract.setParams(WebUtil.getRequestContent(request));
    }
  }

  /**
   * 向log中添加补齐其他的信息（eg：hobbit、server等）
   *
   * @param logAbstract 日志基础类
   */
  public static void addOtherInfoToLog(LogAbstract logAbstract, HobbitProperties hobbitProperties) {
    logAbstract.setServiceId(hobbitProperties.getName());
    logAbstract.setServerHost(INetUtil.getHostName());
    logAbstract.setServerIp(INetUtil.getHostIp());
    logAbstract.setEnv(hobbitProperties.getEnv());
    logAbstract.setCreateTime(DateUtil.now());
    if (logAbstract.getParams() == null) {
      logAbstract.setParams(StringPool.EMPTY);
    }
  }
}
