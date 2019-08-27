package com.gw.cloud.common.core.base.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gw.cloud.common.core.base.dao.BaseMapper;
import com.gw.cloud.common.core.base.entity.AbstractBaseQueryEntity;
import com.gw.cloud.common.core.base.entity.AbstractBaseUpdateEntity;
import com.gw.cloud.common.core.base.result.PageResult;
import com.gw.cloud.common.core.util.StringUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 基本服务抽象类
 *
 * @param <T> 结果实体
 * @param <U> 更新实体
 * @param <Q> 查询实体
 * @author WRQ
 * @date 2019/6/26
 * @since 1.0.0
 */
public abstract class AbstractBaseService<T extends AbstractBaseUpdateEntity<Long>, U extends AbstractBaseUpdateEntity<Long>, Q extends AbstractBaseQueryEntity<Long>> implements BaseService<Long, T, U, Q> {

    protected abstract BaseMapper<Long, T, U, Q> getMapper();

    @Transactional(readOnly = true)
    @Override
    public Long queryCount(Q query) {
        return getMapper().selectCount(query);
    }

    @Transactional(readOnly = true)
    @Override
    public List<T> queryList(Q query) {
        return getMapper().select(query);
    }

    @Transactional(readOnly = true)
    @Override
    public PageResult<T> queryPage(Q query) {
        PageHelper.startPage(query.getPage(), query.getSize());
        List<T> resultList = queryList(query);

        PageResult<T> result = new PageResult<>();
        result.setTotal(new PageInfo<>(resultList).getTotal());
        result.setRows(resultList);
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public T queryById(Long id) {
        return getMapper().selectById(id);
    }

    @Override
    public int deleteById(Long id) {
        return getMapper().deleteById(id);
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public int deleteBatch(String ids) {
        int cnt = 0;
        String[] idArr = ids.split(StringUtil.STR_COMMA);
        for (String id : idArr) {
            if (!StringUtil.isNullOrWhiteSpace(id)) {
                cnt += getMapper().deleteById(Long.valueOf(id));
            }
        }
        return cnt;
    }

    @Override
    public int updateById(U update) {
        return getMapper().updateById(update);
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public int updateBatch(List<U> updateList) {
        int cnt = 0;
        for (U update : updateList) {
            cnt += updateById(update);
        }
        return cnt;
    }

    @Override
    public int save(U update) {
        return getMapper().insert(update);
    }

    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public int saveBatch(List<U> updateList) {
        int cnt = 0;
        for (U update : updateList) {
            cnt += save(update);
        }
        return cnt;
    }
}
