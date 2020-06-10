package com.gw.cloud.common.core.base.result;

import java.io.Serializable;
import java.util.List;

/**
 * 返回给前台的Json结果实体类
 *
 * @param <T>
 *
 * @author WRQ
 * @date 2019/6/26
 * @since 1.0.0
 */
public class MessageResult<T> implements Serializable {

    /**
     * 消息类型
     */
    private String msgType;
    /**
     * 消息主体信息
     */
    private List<T> message;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public List<T> getMessage() {
        return message;
    }

    public void setMessage(List<T> message) {
        this.message = message;
    }


}
