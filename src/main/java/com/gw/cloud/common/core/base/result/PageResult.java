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
    private Long total;
    /**
     * 分页记录列表
     */
    private List<T> rows;

    /**
     * @Description //TODO 分页起始数
     * @Date 16:06 2019/8/26
     * @Param 
     * @return 
     **/
    private int page;
    
    /**
     * @Description //TODO 分页大小
     * @Date 16:06 2019/8/26
     * @Param 
     * @return 
     **/
    private int size;
    
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
