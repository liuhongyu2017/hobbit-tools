package org.hobbit.core.tool.utils;

import java.util.concurrent.TimeUnit;

/**
 * 多线程工具类
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public class ThreadUtil {

  /**
   * Thread sleep
   *
   * @param millis 时长
   */
  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Thread sleep
   *
   * @param timeUnit TimeUnit
   * @param timeout timeout
   */
  public static void sleep(TimeUnit timeUnit, long timeout) {
    try {
      timeUnit.sleep(timeout);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
