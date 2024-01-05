package org.hobbit.core.boot.request;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.Getter;
import org.hobbit.core.tool.utils.WebUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * 全局 Request 包装
 *
 * @author lhy
 * @version 1.0.0 2023/10/04
 */
public class HobbitHttpServletRequestWrapper extends HttpServletRequestWrapper {

  /**
   * 没被包装过的HttpServletRequest（特殊场景,需要自己过滤） -- GETTER -- 获取初始request
   */
  @Getter
  private final HttpServletRequest orgRequest;
  /**
   * 缓存报文,支持多次读取流
   */
  private byte[] body;


  public HobbitHttpServletRequestWrapper(HttpServletRequest request) {
    super(request);
    orgRequest = request;
  }

  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    if (super.getHeader(HttpHeaders.CONTENT_TYPE) == null) {
      return super.getInputStream();
    }

    if (super.getHeader(HttpHeaders.CONTENT_TYPE).startsWith(MediaType.MULTIPART_FORM_DATA_VALUE)) {
      return super.getInputStream();
    }

    if (body == null) {
      body = WebUtil.getRequestBody(super.getInputStream()).getBytes();
    }

    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);

    return new ServletInputStream() {

      @Override
      public int read() {
        return byteArrayInputStream.read();
      }

      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener readListener) {
      }
    };
  }

  /**
   * 获取初始request
   *
   * @param request request
   * @return HttpServletRequest
   */
  public static HttpServletRequest getOrgRequest(HttpServletRequest request) {
    if (request instanceof HobbitHttpServletRequestWrapper) {
      return ((HobbitHttpServletRequestWrapper) request).getOrgRequest();
    }
    return request;
  }
}
