package com.gw.cloud.common.core.handler;

import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.constant.BaseMsgConstant;
import com.gw.cloud.common.core.util.JsonResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;

/**
 * 异常处理类
 *
 * @author WRQ
 * @date 2019/6/25
 * @since 1.0.0
 */
@ControllerAdvice
public class BaseExceptionHandler {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseExceptionHandler.class);

    /**
     * 捕获简单参数验证异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public JsonResult<Object> handler(ValidationException e) {
        LOGGER.error(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_VALIDATION, e.getMessage());
        return JsonResultUtil.createFailureJsonResult(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_VALIDATION);
    }
}
