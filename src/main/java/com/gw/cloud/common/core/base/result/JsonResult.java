package com.gw.cloud.common.core.base.result;

import java.io.Serializable;

/**
 * 返回给前台的Json结果实体类
 *
 * @param <T>
 *
 * @author WRQ
 * @date 2019/6/26
 * @since 1.0.0
 */
public class JsonResult<T> implements Serializable {

    /**
     * 返回标识
     */
    private boolean success;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 状态码
     */
    private String statusCode;
    /**
     * 返回信息
     */
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "success=" + success +
                ", data=" + data +
                ", statusCode='" + statusCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
