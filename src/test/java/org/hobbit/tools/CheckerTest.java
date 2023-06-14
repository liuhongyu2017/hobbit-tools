package org.hobbit.tools;

import lombok.Data;
import org.hobbit.tools.checker.Checkers;
import org.junit.jupiter.api.Test;

/**
 * 参数检查测试方法
 *
 * @author lhy
 * @version 1.0.0 2023/6/14
 */
public class CheckerTest {

  @Test
  public void test() {
    SysUser sysUser = new SysUser();
    sysUser.setId(-1L);
    sysUser.setName("张三");
    sysUser.setAge(10);

    Checkers.<SysUser>lamdbaCheck()
        .notNull(SysUser::getId)
        .check(sysUser);
  }

  @Data
  static class SysUser {

    private Long id;

    private String name;

    private Integer age;
  }
}
