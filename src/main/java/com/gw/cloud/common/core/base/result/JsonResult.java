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
     * @Description //TODO   0:成功   1：失败
     * @Date 8:44 2019/8/27
     * @Param
     * @return
     **/
    private int code;

    /**
     * 返回数据
     */
    private T data;
    /**
     * 状态码
     */
    private int statusCode;
    /**
     * 返回信息
     */
    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "code=" + code +
                ", data=" + data +
                ", statusCode='" + statusCode + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
