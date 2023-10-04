package org.hobbit.core.log.error;

import org.hobbit.core.log.exception.ServiceException;
import org.hobbit.core.tool.api.R;
import org.hobbit.core.tool.api.ResultCode;
import org.hobbit.core.tool.checker.CheckerException;
import org.hobbit.core.tool.utils.Func;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.DispatcherServlet;
import jakarta.servlet.Servlet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 未知异常转移和发送，方便监听，对未知异常统一处理。
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
@Slf4j
@Order
@RequiredArgsConstructor
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@RestControllerAdvice
public class HobbitRestExceptionTranslator {

  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public R<?> handlerError(IllegalArgumentException e) {
    log.error("参数异常", e);
    return R.fail(ResultCode.FAILURE, e.getMessage());
  }

  @ExceptionHandler(CheckerException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public R<?> handlerError(CheckerException e) {
    log.error("参数异常", e);
    return R.fail(ResultCode.FAILURE, e.getMessage());
  }

  @ExceptionHandler(ServiceException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public R<?> handleError(ServiceException e) {
    log.error("业务异常", e);
    return R.fail(e.getResultCode(), e.getMessage());
  }

  @ExceptionHandler(Throwable.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public R<?> handleError(Throwable e) {
    log.error("服务器异常", e);
    return R.fail(ResultCode.INTERNAL_SERVER_ERROR,
        (Func.isEmpty(e.getMessage()) ? ResultCode.INTERNAL_SERVER_ERROR.getMessage()
            : e.getMessage()));
  }
}
