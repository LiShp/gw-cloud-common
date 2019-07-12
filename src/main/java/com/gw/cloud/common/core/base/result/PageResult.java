package com.gw.cloud.common.core.base.result;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果实体类
 *
 * @param <T>
 *
 * @author WRQ
 * @date 2019/6/26
 * @since 1.0.0
 */
public class PageResult<T> implements Serializable {

    /**
     * 总记录数
     */
    private Long totalCount;
    /**
     * 分页记录列表
     */
    private List<T> records;
    
    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "totalCount=" + totalCount +
                ", records=" + records +
                '}';
    }
}
