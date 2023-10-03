package org.hobbit.core.tool.desensitize.desensitization;

import org.apache.commons.lang3.RegExUtils;
import org.hobbit.core.tool.desensitize.Symbol;

/**
 * 身份证脱敏实现
 *
 * @author lhy
 * @version 1.0.0 2022/3/30 10:25
 */
public class IdCardDesensitization implements StringDesensitization {

  @Override
  public String desensitize(String target) {
    return RegExUtils.replaceAll(target, "(?<=\\w{6})\\w(?=\\w{4})", Symbol.STAR);
  }
}
