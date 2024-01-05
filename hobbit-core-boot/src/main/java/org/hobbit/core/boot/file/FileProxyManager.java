package org.hobbit.core.boot.file;

import java.io.File;
import lombok.Getter;
import lombok.Setter;

/**
 * 文件管理类
 *
 * @author lhy
 * @version 1.0.0 2023/10/04
 */
@Setter
@Getter
public class FileProxyManager {

  private IFileProxy defaultFileProxyFactory = new LocalFileProxyFactory();

  private static final FileProxyManager ME = new FileProxyManager();

  public static FileProxyManager me() {
    return ME;
  }

  public String[] path(File file, String dir) {
    return defaultFileProxyFactory.path(file, dir);
  }

  public File rename(File file, String path) {
    return defaultFileProxyFactory.rename(file, path);
  }
}
