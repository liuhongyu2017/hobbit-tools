package org.hobbit.core.tool.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 一些常用的单例对象
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public class Holder {

  /**
   * RANDOM
   */
  public final static Random RANDOM = new Random();

  /**
   * SECURE_RANDOM
   */
  public final static SecureRandom SECURE_RANDOM = new SecureRandom();
}
