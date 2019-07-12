package com.gw.cloud.common.core.base.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 基本查询实体抽象类
 *
 * @param <K> 主键类型
 *
 * @author WRQ
 * @date 2019/6/25
 * @since 1.0.0
 */
public abstract class AbstractBaseQueryEntity<K extends Serializable> extends AbstractBaseUpdateEntity<K> {

    /**
     * 开始时间查询条件
     */
    @ApiModelProperty(value = "开始时间查询条件", name = "beginTime")
    protected String beginTime;

    /**
     * 截止时间查询条件
     */
    @ApiModelProperty(value = "截止时间查询条件", name = "endTime")
    protected String endTime;

    /**
     * 排序条件
     */
    @ApiModelProperty(value = "排序条件", name = "sortString")
    protected String sortString;

    /**
     * 模糊查询条件
     */
    @ApiModelProperty(value = "模糊查询条件", name = "fuzzy")
    protected String fuzzy;

    /**
     * 分页起始数
     */
    @ApiModelProperty(value = "分页起始数", name = "start")
    protected int start = 0;
    /**
     * 分页大小
     */
    @ApiModelProperty(value = "分页大小", name = "limit")
    protected int limit = 20;

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSortString() {
        return sortString;
    }

    public void setSortString(String sortString) {
        this.sortString = sortString;
    }

    public String getFuzzy() {
        return fuzzy;
    }

    public void setFuzzy(String fuzzy) {
        this.fuzzy = fuzzy;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "AbstractBaseQueryEntity{" +
                "beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", sortString='" + sortString + '\'' +
                ", fuzzy='" + fuzzy + '\'' +
                ", start=" + start +
                ", limit=" + limit +
                ", " + super.toString() +
                '}';
    }
}
