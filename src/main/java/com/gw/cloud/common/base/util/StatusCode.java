package com.gw.cloud.common.base.enume;

public enum StatusCode {

    SUCCESS(200, "成功"),EXCEPTION(500, "异常");

    private Integer id;
    private String name;

    StatusCode(Integer id, String name) {
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
