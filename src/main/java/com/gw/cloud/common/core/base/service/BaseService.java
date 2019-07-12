package com.gw.cloud.common.core.base.service;

import com.gw.cloud.common.core.base.entity.AbstractBaseQueryEntity;
import com.gw.cloud.common.core.base.entity.AbstractBaseUpdateEntity;
import com.gw.cloud.common.core.base.result.PageResult;

import java.io.Serializable;
import java.util.List;

/**
 * 基本服务接口
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
public interface BaseService<K extends Serializable, T extends AbstractBaseUpdateEntity<K>, U extends AbstractBaseUpdateEntity<K>, Q extends AbstractBaseQueryEntity<K>> {

    /**
     * 根据参数查询结果行数
     *
     * @param query 查询实体
     * @return 结果行数
     */
    Long queryCount(Q query);

    /**
     * 根据参数查询结果列表
     *
     * @param query 查询实体
     * @return 结果列表
     */
    List<T> queryList(Q query);

    /**
     * 根据参数分页查询结果列表
     *
     * @param query 查询实体
     * @return 分页结果
     */
    PageResult<T> queryPage(Q query);

    /**
     * 根据主键查询结果实体
     *
     * @param id 主键
     * @return 结果实体
     */
    T queryById(K id);

    /**
     * 根据主键删除数据行
     *
     * @param id 主键
     * @return 受影响的行数
     */
    int deleteById(K id);

    /**
     * 根据主键批量删除数据行
     *
     * @param ids 主键
     * @return 受影响的行数
     */
    int deleteBatch(String ids);

    /**
     * 根据参数更新数据行
     *
     * @param update 更新实体
     * @return 受影响的行数
     */
    int updateById(U update);

    /**
     * 根据参数批量更新数据行
     *
     * @param updateList 更新实体列表
     * @return 受影响的行数
     */
    int updateBatch(List<U> updateList);

    /**
     * 根据参数插入数据行
     *
     * @param update 更新实体
     */
    int save(U update);

    /**
     * 根据参数批量插入数据行
     *
     * @param updateList 更新实体列表
     */
    int saveBatch(List<U> updateList);
}
