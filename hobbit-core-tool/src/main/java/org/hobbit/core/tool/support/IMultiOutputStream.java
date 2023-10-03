package org.hobbit.core.tool.support;

import java.io.OutputStream;

/**
 * A factory for creating MultiOutputStream objects.
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public interface IMultiOutputStream {

  /**
   * Builds the output stream.
   *
   * @param params the params
   * @return the output stream
   */
  OutputStream buildOutputStream(Integer... params);
}
