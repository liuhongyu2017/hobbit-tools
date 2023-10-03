package org.hobbit.core.tool.jackson;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;

/**
 * 大数值序列化，避免超过js的精度，造成精度丢失
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public class BigNumberSerializer extends NumberSerializer {

  /**
   * js 最大值为 Math.pow(2, 53)，十进制为：9007199254740992
   */
  private static final long JS_NUM_MAX = 0x20000000000000L;
  /**
   * js 最小值为 -Math.pow(2, 53)，十进制为：-9007199254740992
   */
  private static final long JS_NUM_MIN = -0x20000000000000L;
  /**
   * Static instance that is only to be used for {@link Number}.
   */
  public final static BigNumberSerializer instance = new BigNumberSerializer(Number.class);

  public BigNumberSerializer(Class<? extends Number> rawType) {
    super(rawType);
  }

  @Override
  public void serialize(Number value, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    long longValue = value.longValue();
    if (longValue < JS_NUM_MIN || longValue > JS_NUM_MAX) {
      gen.writeString(value.toString());
    } else {
      super.serialize(value, gen, provider);
    }
  }
}
