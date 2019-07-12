package com.gw.cloud.common.core.base.entity;

import com.gw.cloud.common.core.base.enums.BaseNodeTypeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 树结构查询实体抽象类
 *
 * @param <K> 主键类型
 *
 * @author WRQ
 * @date 2019/6/26
 * @since 1.0.0
 */
public abstract class AbstractTreeQueryEntity<K extends Serializable> extends AbstractBaseQueryEntity<K> {

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码", name = "code")
    protected String code;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", name = "name")
    protected String name;
    /**
     * 拼音缩写
     */
    @ApiModelProperty(value = "拼音缩写", name = "simpleSpellCode")
    protected String simpleSpellCode;
    /**
     * 层级
     */
    @ApiModelProperty(value = "层级", name = "level")
    protected Integer level;
    /**
     * 路径:0|1|2
     */
    @ApiModelProperty(value = "路径", name = "path")
    protected String path;
    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID", name = "parentId")
    protected K parentId;
    /**
     * 顺序号
     */
    @ApiModelProperty(value = "顺序号", name = "sortNo")
    protected Integer sortNo;
    /**
     * 节点类型
     */
    @ApiModelProperty(value = "节点类型", name = "nodeType")
    protected BaseNodeTypeEnum nodeType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimpleSpellCode() {
        return simpleSpellCode;
    }

    public void setSimpleSpellCode(String simpleSpellCode) {
        this.simpleSpellCode = simpleSpellCode;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public K getParentId() {
        return parentId;
    }

    public void setParentId(K parentId) {
        this.parentId = parentId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public BaseNodeTypeEnum getNodeType() {
        return nodeType;
    }

    public void setNodeType(BaseNodeTypeEnum nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "AbstractTreeQueryEntity{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", simpleSpellCode='" + simpleSpellCode + '\'' +
                ", level=" + level +
                ", path='" + path + '\'' +
                ", parentId=" + parentId +
                ", sortNo=" + sortNo +
                ", nodeType=" + nodeType +
                ", " + super.toString() +
                '}';
    }
}
