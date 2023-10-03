package org.hobbit.core.tool.checker;

/**
 * 参数校验异常
 *
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
public class CheckerException extends RuntimeException {

  public CheckerException(String message) {
    super(message);
  }
}
