package com.gw.cloud.common.core.base.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 基本实体抽象类
 *
 * @param <K> 主键类型
 *
 * @author WRQ
 * @date 2019/6/25
 * @since 1.0.0
 */
public abstract class AbstractBaseEntity<K extends Serializable> implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", name = "id")
    protected K id;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", name = "remark")
    protected String remark;

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AbstractBaseEntity other = (AbstractBaseEntity) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AbstractBaseEntity{" +
                "id=" + id +
                "remark=" + remark +
                '}';
    }
}
