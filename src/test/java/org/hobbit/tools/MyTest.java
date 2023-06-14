package org.hobbit.tools;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

/**
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
public class MyTest {

  @Test
  public void test() {
    String[] s = StringUtils.split("张三", ",");
    System.out.println(Arrays.toString(s));
  }
}
