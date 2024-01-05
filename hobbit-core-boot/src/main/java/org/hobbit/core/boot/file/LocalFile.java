package org.hobbit.core.boot.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hobbit.core.context.props.HobbitFileProperties;
import org.hobbit.core.tool.utils.DateUtil;
import org.hobbit.core.tool.utils.SpringUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件封装
 *
 * @author lhy
 * @version 1.0.0 2023/10/04
 */
@Slf4j
@Data
public class LocalFile {

  /**
   * 上传文件在附件表中的id
   */
  private Object fileId;

  /**
   * 上传文件
   */
  @JsonIgnore
  private MultipartFile file;

  /**
   * 文件外网地址
   */
  private String domain;

  /**
   * 上传分类文件夹
   */
  private String dir;

  /**
   * 上传物理路径
   */
  private String uploadPath;

  /**
   * 上传虚拟路径
   */
  private String uploadVirtualPath;

  /**
   * 文件名
   */
  private String fileName;

  /**
   * 真实文件名
   */
  private String originalFileName;

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

  public LocalFile(MultipartFile file, String dir) {
    this.dir = dir;
    this.file = file;
    this.fileName = file.getName();
    this.originalFileName = file.getOriginalFilename();
    this.domain = getHobbitFileProperties().getUploadDomain();
    this.uploadPath = HobbitFileUtil.formatUrl(File.separator
        + getHobbitFileProperties().getUploadRealPath() + File.separator + dir + File.separator
        + DateUtil.format(DateUtil.now(), "yyyyMMdd") + File.separator + this.originalFileName);
    this.uploadVirtualPath =
        HobbitFileUtil.formatUrl(getHobbitFileProperties().getUploadCtxPath().replace(
            getHobbitFileProperties().getContextPath(), "") + File.separator + dir + File.separator
            + DateUtil.format(DateUtil.now(), "yyyyMMdd") + File.separator + this.originalFileName);
  }

  public LocalFile(MultipartFile file, String dir, String uploadPath, String uploadVirtualPath) {
    this(file, dir);
    if (null != uploadPath) {
      this.uploadPath = HobbitFileUtil.formatUrl(uploadPath);
      this.uploadVirtualPath = HobbitFileUtil.formatUrl(uploadVirtualPath);
    }
  }

  /**
   * 图片上传
   */
  public void transfer() {
    transfer(getHobbitFileProperties().getCompress());
  }

  /**
   * 图片上传
   *
   * @param compress 是否压缩
   */
  public void transfer(boolean compress) {
    IFileProxy fileFactory = FileProxyManager.me().getDefaultFileProxyFactory();
    this.transfer(fileFactory, compress);
  }

  /**
   * 图片上传
   *
   * @param fileFactory 文件上传工厂类
   * @param compress    是否压缩
   */
  public void transfer(IFileProxy fileFactory, boolean compress) {
    try {
      File file = new File(uploadPath);

      if (null != fileFactory) {
        String[] path = fileFactory.path(file, dir);
        this.uploadPath = path[0];
        this.uploadVirtualPath = path[1];
        file = fileFactory.rename(file, path[0]);
      }

      File pfile = file.getParentFile();
      if (!pfile.exists()) {
        pfile.mkdirs();
      }

      this.file.transferTo(file);

      if (compress) {
        Objects.requireNonNull(fileFactory).compress(this.uploadPath);
      }

    } catch (IllegalStateException | IOException e) {
      log.error(e.getMessage(), e);
    }
  }
}
