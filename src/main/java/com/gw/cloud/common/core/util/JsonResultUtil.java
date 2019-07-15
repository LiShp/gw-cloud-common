package com.gw.cloud.common.core.util;

import com.gw.cloud.common.core.base.result.JsonResult;
import com.gw.cloud.common.core.constant.BaseMsgConstant;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

/**
 * JsonResult工具类
 *
 * @author WRQ
 * @date 2019/6/26
 * @since 1.0.0
 */
public class JsonResultUtil {

    private static final String MSG_ERROR_KEY_MYSQL_INTEGRITY_CONSTRAINT_VIOLATION = "Duplicate entry";

    public static <T> JsonResult<T> createJsonResult(boolean success, T data, String statusCode, String message) {
        JsonResult<T> jsonResult = new JsonResult<T>();
        jsonResult.setSuccess(success);
        jsonResult.setData(data);
        jsonResult.setStatusCode(statusCode);
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static <T> JsonResult<T> createSuccessJsonResult(T data, String statusCode, String message) {
        return createJsonResult(true, data, statusCode, message);
    }

    public static <T> JsonResult<T> createSuccessJsonResult(T data, String message) {
        return createSuccessJsonResult(data, String.valueOf(HttpStatus.OK.value()), message);
    }

    public static <T> JsonResult<T> createSuccessJsonResult(T data) {
        return createSuccessJsonResult(data, BaseMsgConstant.BASE_MSG_INFO_DEFAULT);
    }

    public static <T> JsonResult<T> createFailureJsonResult(T data, String statusCode, String message) {
        return createJsonResult(false, data, statusCode, message);
    }

    public static <T> JsonResult<T> createFailureJsonResult(T data, String message) {
        return createFailureJsonResult(data, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), message);
    }

    public static <T> JsonResult<T> createFailureJsonResult(String message) {
        return createFailureJsonResult(null, message);
    }

    public static <T> JsonResult<T> createFailureJsonResult(String format, Throwable e) {
        if (e != null && e.getMessage() != null) {
            if (DuplicateKeyException.class.equals(e.getClass()) || e.getMessage().contains(MSG_ERROR_KEY_MYSQL_INTEGRITY_CONSTRAINT_VIOLATION)) {
                String param = e.getMessage().split(StringUtil.STR_SINGLE_QUOTE)[1];
                String errMsg = MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DATA_EXISTS, param);
                return createFailureJsonResult(MessageFormat.format(format, errMsg));
            }
        }
        return createFailureJsonResult(MessageFormat.format(format, e.getMessage()));
    }
}