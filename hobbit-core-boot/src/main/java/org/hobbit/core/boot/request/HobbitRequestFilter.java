package org.hobbit.core.boot.request;

import java.io.IOException;
import org.hobbit.core.context.props.RequestProperties;
import org.hobbit.core.context.props.XssProperties;
import org.springframework.util.AntPathMatcher;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

/**
 * Request 全局过滤器
 * 
 * @author lhy
 * @version 1.0.0 2023/10/04
 */
@RequiredArgsConstructor
public class HobbitRequestFilter implements Filter {

  private final RequestProperties requestProperties;
  private final XssProperties xssProperties;
  private final AntPathMatcher antPathMatcher = new AntPathMatcher();

  @Override
  public void init(FilterConfig config) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String path = ((HttpServletRequest) request).getServletPath();
    // 跳过 Request 包装
    if (!requestProperties.getEnabled() || requestSkip(path)) {
      chain.doFilter(request, response);
    }
    // 默认 Request 包装
    else if (!xssProperties.getEnabled() || xssSkip(path)) {
      HobbitHttpServletRequestWrapper hobbitRequest =
          new HobbitHttpServletRequestWrapper((HttpServletRequest) request);
      chain.doFilter(hobbitRequest, response);
    }
    // Xss Request 包装
    else {
      XssHttpServletRequestWrapper xssRequest =
          new XssHttpServletRequestWrapper((HttpServletRequest) request);
      chain.doFilter(xssRequest, response);
    }
  }

  @Override
  public void destroy() {

  }

  private boolean requestSkip(String path) {
    return requestProperties.getSkipUrl().stream()
        .anyMatch(pattern -> antPathMatcher.match(pattern, path));
  }

  private boolean xssSkip(String path) {
    return xssProperties.getSkipUrl().stream()
        .anyMatch(pattern -> antPathMatcher.match(pattern, path));
  }

}
