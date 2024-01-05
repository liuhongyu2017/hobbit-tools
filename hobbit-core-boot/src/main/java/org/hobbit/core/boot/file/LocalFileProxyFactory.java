package org.hobbit.core.boot.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.hobbit.core.context.props.HobbitFileProperties;
import org.hobbit.core.tool.pool.StringPool;
import org.hobbit.core.tool.utils.DateUtil;
import org.hobbit.core.tool.utils.ImageUtil;
import org.hobbit.core.tool.utils.SpringUtil;

/**
 * 文件代理类
 *
 * @author lhy
 * @version 1.0.0 2023/10/04
 */
@Slf4j
public class LocalFileProxyFactory implements IFileProxy {

  /**
   * 文件配置
   */
  private static HobbitFileProperties fileProperties;

  private static HobbitFileProperties getHobbitFileProperties() {
    if (fileProperties == null) {
      fileProperties = SpringUtil.getBean(HobbitFileProperties.class);
    }
    return fileProperties;
  }

  @Override
  public File rename(File f, String path) {
    File dest = new File(path);
    f.renameTo(dest);
    return dest;
  }

  @Override
  public String[] path(File f, String dir) {
    // 避免网络延迟导致时间不同步
    long time = System.nanoTime();

    String uploadPath = getFileDir(dir, getHobbitFileProperties().getUploadRealPath())
        + time + getFileExt(f.getName());

    String virtualPath = getFileDir(dir, getHobbitFileProperties().getUploadCtxPath())
        + time + getFileExt(f.getName());

    return new String[]{HobbitFileUtil.formatUrl(uploadPath),
        HobbitFileUtil.formatUrl(virtualPath)};
  }

  /**
   * 获取文件后缀
   *
   * @param fileName 文件名
   * @return 文件后缀
   */
  public static String getFileExt(String fileName) {
    if (!fileName.contains(StringPool.DOT)) {
      return ".jpg";
    } else {
      return fileName.substring(fileName.lastIndexOf(StringPool.DOT));
    }
  }

  /**
   * 获取文件保存地址
   *
   * @param dir     目录
   * @param saveDir 保存目录
   * @return 地址
   */
  public static String getFileDir(String dir, String saveDir) {
    return saveDir + File.separator + dir + File.separator
        + DateUtil.format(DateUtil.now(), "yyyyMMdd") + File.separator;
  }


  /**
   * 图片压缩
   *
   * @param path 文件地址
   */
  @Override
  public void compress(String path) {
    try {
      ImageUtil.zoomScale(Objects.requireNonNull(ImageUtil.readImage(path)),
          new FileOutputStream(path), null,
          getHobbitFileProperties().getCompressScale(),
          getHobbitFileProperties().getCompressFlag());
    } catch (FileNotFoundException e) {
      log.error(e.getMessage(), e);
    }
  }
}
