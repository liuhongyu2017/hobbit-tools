package org.hobbit.core.tool.api;

import java.util.Optional;
import org.hobbit.core.tool.constant.HobbitConstant;
import org.hobbit.core.tool.utils.ObjectUtil;
import org.springframework.lang.Nullable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@ApiModel(description = "返回信息")
public class R<T> {

  @ApiModelProperty(value = "状态码", required = true)
  private int code;
  @ApiModelProperty(value = "是否成功", required = true)
  private boolean success;
  @ApiModelProperty(value = "承载数据")
  private T data;
  @ApiModelProperty(value = "返回消息", required = true)
  private String msg;

  private R(IResultCode resultCode) {
    this(resultCode, null, resultCode.getMessage());
  }

  private R(IResultCode resultCode, String msg) {
    this(resultCode, null, msg);
  }

  private R(IResultCode resultCode, T data) {
    this(resultCode, data, resultCode.getMessage());
  }

  private R(IResultCode resultCode, T data, String msg) {
    this(resultCode.getCode(), data, msg);
  }

  private R(int code, T data, String msg) {
    this.code = code;
    this.data = data;
    this.msg = msg;
    this.success = ResultCode.SUCCESS.code == code;
  }

  /**
   * 判断返回是否为成功
   *
   * @param result Result
   * @return 是否成功
   */
  public static boolean isSuccess(@Nullable R<?> result) {
    return Optional.ofNullable(result)
        .map(x -> ObjectUtil.nullSafeEquals(ResultCode.SUCCESS.code, x.code)).orElse(Boolean.FALSE);
  }

  /**
   * 判断返回是否为成功
   *
   * @param result Result
   * @return 是否成功
   */
  public static boolean isNotSuccess(@Nullable R<?> result) {
    return !R.isSuccess(result);
  }

  /**
   * 返回R
   *
   * @param data 数据
   * @param <T> T 泛型标记
   * @return R
   */
  public static <T> R<T> data(T data) {
    return data(data, HobbitConstant.DEFAULT_SUCCESS_MESSAGE);
  }

  /**
   * 返回R
   *
   * @param data 数据
   * @param msg 消息
   * @param <T> T 泛型标记
   * @return R
   */
  public static <T> R<T> data(T data, String msg) {
    return data(HttpServletResponse.SC_OK, data, msg);
  }

  /**
   * 返回R
   *
   * @param code 状态码
   * @param data 数据
   * @param msg 消息
   * @param <T> T 泛型标记
   * @return R
   */
  public static <T> R<T> data(int code, T data, String msg) {
    return new R<>(code, data, data == null ? HobbitConstant.DEFAULT_NULL_MESSAGE : msg);
  }

  /**
   * 返回R
   *
   * @param msg 消息
   * @param <T> T 泛型标记
   * @return R
   */
  public static <T> R<T> success(String msg) {
    return new R<>(ResultCode.SUCCESS, msg);
  }

  /**
   * 返回R
   *
   * @param resultCode 业务代码
   * @param <T> T 泛型标记
   * @return R
   */
  public static <T> R<T> success(IResultCode resultCode) {
    return new R<>(resultCode);
  }

  /**
   * 返回R
   *
   * @param resultCode 业务代码
   * @param msg 消息
   * @param <T> T 泛型标记
   * @return R
   */
  public static <T> R<T> success(IResultCode resultCode, String msg) {
    return new R<>(resultCode, msg);
  }

  /**
   * 返回R
   *
   * @param msg 消息
   * @param <T> T 泛型标记
   * @return R
   */
  public static <T> R<T> fail(String msg) {
    return new R<>(ResultCode.FAILURE, msg);
  }

  /**
   * 返回R
   *
   * @param code 状态码
   * @param msg 消息
   * @param <T> T 泛型标记
   * @return R
   */
  public static <T> R<T> fail(int code, String msg) {
    return new R<>(code, null, msg);
  }

  /**
   * 返回R
   *
   * @param resultCode 业务代码
   * @param <T> T 泛型标记
   * @return R
   */
  public static <T> R<T> fail(IResultCode resultCode) {
    return new R<>(resultCode);
  }

  /**
   * 返回R
   *
   * @param resultCode 业务代码
   * @param msg 消息
   * @param <T> T 泛型标记
   * @return R
   */
  public static <T> R<T> fail(IResultCode resultCode, String msg) {
    return new R<>(resultCode, msg);
  }

  /**
   * 返回R
   *
   * @param flag 成功状态
   * @return R
   */
  public static <T> R<T> status(boolean flag) {
    return flag ? success(HobbitConstant.DEFAULT_SUCCESS_MESSAGE)
        : fail(HobbitConstant.DEFAULT_FAILURE_MESSAGE);
  }
}
