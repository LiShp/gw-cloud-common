package com.gw.cloud.common.core.base.enums;

/**
 * 平台编码枚举类
 * @author dcc
 * @date 2019-11-07 14:38
 */
public enum PlatformCodeEnum {
    MDM(0, "mdm"),
    LMD(1, "lmd");

    private Integer id;
    private String name;

    PlatformCodeEnum(Integer id, String name) {
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
