package com.gw.cloud.common.core.base.dao;

import com.gw.cloud.common.core.base.entity.AbstractBaseQueryEntity;
import com.gw.cloud.common.core.base.entity.AbstractBaseUpdateEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 基本Mapper接口
 *
 * @param <K> 主键类型
 * @param <T> 结果实体
 * @param <U> 更新实体
 * @param <Q> 查询实体
 *
 * @author WRQ
 * @date 2019/6/26
 * @since 1.0.0
 */
public interface BaseMapper<K extends Serializable, T extends AbstractBaseUpdateEntity<K>, U extends AbstractBaseUpdateEntity<K>, Q extends AbstractBaseQueryEntity<K>> {

    /**
     * 根据参数查询结果行数
     *
     * @param query 查询实体
     * @return 结果行数
     */
    Long selectCount(Q query);

    /**
     * 根据参数查询结果列表
     *
     * @param query 查询实体
     * @return 结果列表
     */
    List<T> select(Q query);

    /**
     * 根据主键查询结果实体
     *
     * @param id 主键
     * @return 结果实体
     */
    T selectById(K id);

    /**
     * 根据主键删除数据行
     *
     * @param id 主键
     * @return 受影响的行数
     */
    int deleteById(K id);

    /**
     * 根据参数更新数据行
     *
     * @param update 更新实体
     * @return 受影响的行数
     */
    int updateById(U update);

    /**
     * 根据参数插入数据行
     *
     * @param update 更新实体
     */
    int insert(U update);
}
