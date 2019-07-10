package com.gw.cloud.common.base.util;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果实体类
 *
 * @param <T>
 * @author cgb
 */
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -3330502296707531376L;

    private Long totalCount;

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
