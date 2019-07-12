package com.gw.cloud.common.base.enums;

/**
 * 返回状态枚举类
 *
 * @author jyw
 * @date 2019/4/16
 * @since 1.0.0
 */
public enum StatusCodeEnum {

    SUCCESS(200, "成功"),EXCEPTION(500, "异常");

    private Integer id;
    private String name;

    StatusCodeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
