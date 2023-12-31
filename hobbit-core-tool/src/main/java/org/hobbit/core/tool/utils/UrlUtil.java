package org.hobbit.core.tool.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import org.springframework.web.util.UriUtils;

/**
 * url处理工具类
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public class UrlUtil extends UriUtils {

  /**
   * url 编码
   *
   * @param source source
   * @return sourced String
   */
  public static String encode(String source) {
    return UrlUtil.encode(source, Charsets.UTF_8);
  }

  /**
   * url 解码
   *
   * @param source source
   * @return decoded String
   */
  public static String decode(String source) {
    return UrlUtil.decode(source, Charsets.UTF_8);
  }

  /**
   * url 编码
   *
   * @param source  url
   * @param charset 字符集
   * @return 编码后的url
   */
  public static String encodeURL(String source, Charset charset) {
    return UrlUtil.encode(source, charset.name());
  }

  /**
   * url 解码
   *
   * @param source  url
   * @param charset 字符集
   * @return 解码url
   */
  @Deprecated
  public static String decodeURL(String source, Charset charset) {
    return UrlUtil.decode(source, charset.name());
  }

  /**
   * 获取url路径
   *
   * @param uriStr 路径
   * @return url路径
   */
  public static String getPath(String uriStr) {
    URI uri;

    try {
      uri = new URI(uriStr);
    } catch (URISyntaxException var3) {
      throw new RuntimeException(var3);
    }

    return uri.getPath();
  }
}
