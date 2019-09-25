package com.gw.cloud.common.core.enume;

/**
 * Token 操作类型枚举
 * @author dcc
 * @date 2019/9/25
 */
public enum OperType {
    ADD(0, "添加操作"),
    UPDATE(1, "编辑操作");

    private Integer id;
    private String name;

    OperType(Integer id, String name) {
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
