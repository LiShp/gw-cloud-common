package com.gw.cloud.common.core.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gw.cloud.common.core.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 基本创建实体抽象类
 *
 * @param <K> 主键类型
 *
 * @author WRQ
 * @date 2019/6/25
 * @since 1.0.0
 */
public abstract class AbstractBaseCreateEntity<K extends Serializable> extends AbstractBaseEntity<K> {

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime")
    @DateTimeFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.DEFAULT_FORMAT_PATTERN_DATETIME, timezone = DateUtil.DEFAULT_TIME_ZONE_TYPE)
    protected Date createTime;
    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID", name = "creatorId")
    protected Long creatorId;
    /**
     * 创建人账号
     */
    @ApiModelProperty(value = "创建人账号", name = "creatorCode")
    protected String creatorCode;
    /**
     * 创建人姓名
     */
    @ApiModelProperty(value = "创建人姓名", name = "creatorName")
    protected String creatorName;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorCode() {
        return creatorCode;
    }

    public void setCreatorCode(String creatorCode) {
        this.creatorCode = creatorCode;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    @Override
    public String toString() {
        return "AbstractBaseCreateEntity{" +
                ", createTime=" + createTime +
                ", creatorId=" + creatorId +
                ", creatorCode='" + creatorCode + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", " + super.toString() +
                '}';
    }
}
