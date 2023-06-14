package org.hobbit.tools.checker;

/**
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
public class Checkers {

  public static <T> Checker<T> lamdbaCheck() {
    return new Checker<>();
  }
}
