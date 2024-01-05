package org.hobbit.core.log.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import org.hobbit.core.tool.utils.JsonUtil;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 更改 html 请求异常为 ajax
 *
 * @author lhy
 * @version 1.0.0 2023/9/3
 */
public class HobbitErrorController extends BasicErrorController {

  public HobbitErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
    super(errorAttributes, errorProperties);
  }

  @Override
  public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
    boolean includeStackTrace = isIncludeStackTrace(request, MediaType.ALL);
    Map<String, Object> body = getErrorAttributes(request,
        includeStackTrace ? ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE)
            : ErrorAttributeOptions.defaults());
    HttpStatus status = this.getStatus(request);
    response.setStatus(status.value());
    MappingJackson2JsonView view = new MappingJackson2JsonView();
    view.setObjectMapper(JsonUtil.getInstance());
    view.setContentType(MediaType.APPLICATION_JSON_VALUE);
    return new ModelAndView(view, body);
  }

}
