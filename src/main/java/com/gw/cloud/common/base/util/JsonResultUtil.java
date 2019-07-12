package com.gw.cloud.common.base.util;

import com.gw.cloud.common.base.constant.BaseMsgConstant;
import com.gw.cloud.common.base.enums.StatusCodeEnum;
import org.springframework.dao.DuplicateKeyException;

import java.text.MessageFormat;

/**
 * JsonResult工具类
 *
 * @author WRQ
 */
public class JsonResultUtil {

    private static final String MSG_ERROR_KEY_MYSQL_INTEGRITY_CONSTRAINT_VIOLATION = "Duplicate entry";

    public static <T> JsonResult<T> createJsonResult(T data, boolean isSuccess, String msg) {
        JsonResult<T> temResult = new JsonResult<T>();
        temResult.setData(data);
        temResult.setSuccess(isSuccess);
        temResult.setMessage(msg);
        if(isSuccess){
            temResult.setStatusCode(StatusCodeEnum.SUCCESS.getId());
        }else{
            temResult.setStatusCode(StatusCodeEnum.EXCEPTION.getId());
        }
        return temResult;
    }

    public static <T> JsonResult<T> createSuccessJsonResult(T data, String msg) {
        return createJsonResult(data, true, msg);
    }

    public static <T> JsonResult<T> createFailureJsonResult(T data, String msg) {
        return createJsonResult(data, false, msg);
    }

    public static <T> JsonResult<T> createSuccessJsonResult(T data) {
        return createSuccessJsonResult(data, BaseMsgConstant.BASE_MSG_INFO_DEFAULT);
    }

    public static <T> JsonResult<T> createFailureJsonResult(T data) {
        return createFailureJsonResult(data, BaseMsgConstant.BASE_MSG_ERROR_DEFAULT);
    }

    public static <T> JsonResult<T> createSuccessJsonResult() {
        return createSuccessJsonResult(null, BaseMsgConstant.BASE_MSG_INFO_DEFAULT);
    }

    public static <T> JsonResult<T> createFailureJsonResult(String msg) {
        return createFailureJsonResult(null, msg);
    }

    public static <T> JsonResult<T> createFailureJsonResult(String format, Exception e) {
        if (e != null && e.getMessage() != null) {
            if (DuplicateKeyException.class.equals(e.getClass()) || e.getMessage().contains(MSG_ERROR_KEY_MYSQL_INTEGRITY_CONSTRAINT_VIOLATION)) {
                String param = e.getMessage().split(StringUtils.STR_SINGLE_QUOTE)[1];
                String errMsg = MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_DATA_EXISTS, param);
                return createFailureJsonResult(MessageFormat.format(format, errMsg));
            }
        }
        return createFailureJsonResult(MessageFormat.format(format, e.getMessage()));
    }

    public static <T> JsonResult<T> createJsonResult(T data, boolean isSuccess) {
        if (isSuccess) {
            return createSuccessJsonResult(data);
        } else {
            return createFailureJsonResult(data);
        }
    }
}
