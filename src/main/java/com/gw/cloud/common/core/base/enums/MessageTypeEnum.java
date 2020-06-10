package com.gw.cloud.common.core.base.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.gw.cloud.common.core.base.exception.ApplicationException;
import com.gw.cloud.common.core.constant.BaseMsgConstant;

import java.text.MessageFormat;

/**
 * 业务系统标识 枚举类
 * @说明:
 * @Author: SYH
 * @Date: 2019/7/11 15:56
 * @Version 1.0
 */
public enum MessageTypeEnum {

    CREATE(1, "create"),
    EDIT(2, "edit"),
    DELETE(3,"delete");

    private int id;
    private String code;

    @JsonCreator
    public static MessageTypeEnum getItem(Integer id) {
        for (MessageTypeEnum item : values()) {
            if (item.getId()==id) {
                return item;
            }
        }
        throw new ApplicationException(MessageFormat.format(BaseMsgConstant.BASE_MSG_ERROR_FORMAT_UNRECOGNIZED, BaseNodeTypeEnum.class, id));
    }

    MessageTypeEnum(int id, String code) {
        this.id = id;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

}
