package com.gw.cloud.common.base.util;

import java.io.Serializable;

/**
 * 返回给前台结果实体类
 *
 * @param <T>
 * @author cgb
 */
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = 4987891460816484062L;

    private T data;

    private boolean success;

    private Integer statusCode;

    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> JsonResult<T> newResult() {
        // TODO Auto-generated method stub
        JsonResult<T> result = new JsonResult<>();
        result.setSuccess(true);
        return result;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "data=" + data +
                ", success=" + success +
                ", statusCode='" + statusCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
