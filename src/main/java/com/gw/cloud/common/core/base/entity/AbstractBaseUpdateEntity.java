package com.gw.cloud.common.core.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gw.cloud.common.core.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 基本更新实体抽象类
 *
 * @param <K> 主键类型
 *
 * @author WRQ
 * @date 2019/6/25
 * @since 1.0.0
 */
public abstract class AbstractBaseUpdateEntity<K extends Serializable> extends AbstractBaseCreateEntity<K> {

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    protected Date updateTime;
    /**
     * 更新人ID
     */
    @ApiModelProperty(value = "更新人ID", name = "updaterId")
    protected Long updaterId;
    /**
     * 更新人账号
     */
    @ApiModelProperty(value = "更新人账号", name = "updaterCode")
    protected String updaterCode;
    /**
     * 更新人姓名
     */
    @ApiModelProperty(value = "更新人姓名", name = "updaterName")
    protected String updaterName;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Long updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdaterCode() {
        return updaterCode;
    }

    public void setUpdaterCode(String updaterCode) {
        this.updaterCode = updaterCode;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    @Override
    public String toString() {
        return "AbstractBaseUpdateEntity{" +
                "updateTime=" + updateTime +
                ", updaterId=" + updaterId +
                ", updaterCode='" + updaterCode + '\'' +
                ", updaterName='" + updaterName + '\'' +
                ", " + super.toString() +
                '}';
    }
}
