package org.hobbit.core.tool.constant;

/**
 * app 常量
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public interface AppConstant {

  /**
   * 应用版本
   */
  String APPLICATION_VERSION = "3.1.4-SNAPSHOTS";
  /**
   * 基础包
   */
  String BASE_PACKAGES = "org.hobbit,spring.boot";
  /**
   * 开发环境
   */
  String DEV_CODE = "dev";
  /**
   * 生产环境
   */
  String PROD_CODE = "prod";
  /**
   * 测试环境
   */
  String TEST_CODE = "test";

  /**
   * 代码部署于 linux 上，工作默认为 mac 和 Windows
   */
  String OS_NAME_LINUX = "LINUX";
}
