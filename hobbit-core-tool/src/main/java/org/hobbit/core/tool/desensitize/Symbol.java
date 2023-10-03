package org.hobbit.core.tool.desensitize;

/**
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public class Symbol {

  /**
   * 脱敏符号
   */
  public static final String STAR = "*";

  private Symbol() {

  }

  /**
   * 获取符号
   *
   * @param number 符号个数
   * @param symbol 符号
   */
  public static String getSymbol(int number, String symbol) {
    return String.valueOf(symbol).repeat(Math.max(0, number));
  }
}
