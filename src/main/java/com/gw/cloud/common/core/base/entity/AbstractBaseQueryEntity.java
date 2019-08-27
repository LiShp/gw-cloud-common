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
    @ApiModelProperty(value = "分页起始数", name = "page")
    protected int page = 0;
    /**
     * 分页大小
     */
    @ApiModelProperty(value = "分页大小", name = "size")
    protected int size = 20;

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "AbstractBaseQueryEntity{" +
                "beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", sortString='" + sortString + '\'' +
                ", fuzzy='" + fuzzy + '\'' +
                ", page=" + page +
                ", size=" + size +
                ", " + super.toString() +
                '}';
    }
}
