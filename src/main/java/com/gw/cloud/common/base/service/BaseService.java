package com.gw.cloud.common.base.service;

import com.gw.cloud.common.base.entity.BaseEntity;
import com.gw.cloud.common.base.mapper.BaseMapper;
import com.gw.cloud.common.base.util.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * only used for the annotated entity like this:
 * <pre>
 *     {@code
 *     {@literal @}Table(name="t_user")
 *     	public class User extends AutoIncrementKeyBaseDomain{
 *
 *     	}
 *     }
 * </pre>
 *
 * @param <PK>
 * @param <T>
 */
public abstract class BaseService<PK, T extends BaseEntity> implements IBaseService<PK, T > {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected final Class<T> domainType;
    protected final Field pkField;

    @Autowired
    protected BaseMapper<T> mapper;

    public BaseService() {
        this.domainType = (Class<T>) (((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[1]);
        List<Field> fields = FieldUtils.getFieldsListWithAnnotation(domainType,
                Id.class);
        if (fields.size() != 1) {
            throw new IllegalStateException("Can't inherit BaseService ["
                    + domainType.getTypeName() + "]");
        } else {
            pkField = fields.get(0);
        }

    }

    public Mapper<T> getMapper() {
        return mapper;
    }

    public Class<T> getDomainType() {
        return this.domainType;
    }

    @Override
    public T selectOne(T t) {
        return mapper.selectOne(t);
    }

    @Override
    public T selectByPk(PK pk) {
        return mapper.selectByPrimaryKey(pk);
    }

    @Override
    public List<T> selectByPks(Collection<PK> pks) {
        Example example = new Example(domainType);
        example.createCriteria().andIn(pkField.getName(), pks).andCondition("delete_flag = false");
        return mapper.selectByExample(example);
    }

    @Override
    public int countByExample(Example example) {
        return mapper.selectCountByExample(example);
    }

    @Override
    public T selectOneByExample(Example example) {
        List<T> data = mapper.selectByExample(example);
        if (data.isEmpty()) {
            return null;
        } else {
            return data.get(0);
        }
    }

    @Override
    public T selectUniqueByExample(Example example) {
        List<T> data = mapper.selectByExample(example);
        if (data.size() != 1) {
            throw new IncorrectResultSizeDataAccessException(1, data.size());
        }
        return data.get(0);
    }

    @Override
    public List<T> selectListByExample(Example example) {
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> paginateListByExample(Example example, int page, int rows){
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        return mapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    @Override
    public List<T> selectList(T t) {
        return mapper.select(t);
    }

    @Override
    public List<T> paginateList(T t, int page, int rows){
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        return mapper.selectByRowBounds(t, rowBounds);
    }

    @Override
    public QueryResult<T> paginateQueryResultByExample(Example example, int page, int rows) {
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        return paginateByExampleAndRowBounds(example, rowBounds);
    }

    @Override
    public QueryResult<T> paginateQueryResult(T t, int page, int rows) {
        int cnt = mapper.selectCount(t);
        List<T> data;
        if (cnt != 0) {
            RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
            data = mapper.selectByRowBounds(t, rowBounds);
        } else {
            data = Collections.emptyList();
        }
        return new QueryResult<>(cnt, data);
    }

    @Override
    public List<T> selectByExampleAndRowBounds(Example example,
                                               RowBounds rowBounds) {
        return mapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    @Override
    public QueryResult<T> paginateByExampleAndRowBounds(Example example,
                                                        RowBounds rowBounds) {
        int cnt = mapper.selectCountByExample(example);
        List<T> data;
        if (cnt != 0) {
            data = mapper.selectByExampleAndRowBounds(example, rowBounds);
        } else {
            data = Collections.emptyList();
        }
        return new QueryResult<>(cnt, data);
    }

    @Override
    public T create(T entity) {
        Object pk = _getPkValue(entity);
        //主键无值，并且没有添加KeySql, GeneratedValue注解，则由数据库产生主键，插入时忽略id字段。主要针对自动生成自增主键的情况
        if (pk == null && pkField.getAnnotation(KeySql.class) == null && pkField.getAnnotation(GeneratedValue.class) == null) {
            mapper.insertUseGeneratedKeys(entity);
        } else {
            mapper.insert(entity);
        }
        return entity;
    }

    @Override
    public int insertList(List<T> list) {
        return mapper.insertList(list);
    }

    @Override
    public T update(T entity) {
        int rows = mapper.updateByPrimaryKey(entity);
        if (rows == 0) {
            throw new DataRetrievalFailureException("No update for ["
                    + domainType.getTypeName() + "], pk:" + _getPkValue(entity));
        }
        return entity;
    }

    @Override
    public T updateSelective(T entity) {
        int rows = mapper.updateByPrimaryKeySelective(entity);
        if (rows == 0) {
            throw new DataRetrievalFailureException("No update for ["
                    + domainType.getTypeName() + "], pk:" + _getPkValue(entity));
        }
        Object pk = _getPkValue(entity);
        return mapper.selectByPrimaryKey(pk);
    }

    @Override
    public int updateByExample(T entity, Example example) {
        return mapper.updateByExample(entity, example);
    }

    @Override
    public int updateByExampleSelective(T entity, Example example) {
        return mapper.updateByExampleSelective(entity, example);
    }

    @Override
    public void delete(T t) {
        int rows = mapper.delete(t);
        if (rows == 0) {
            throw new DataRetrievalFailureException("No delete for ["
                    + domainType.getTypeName() + "]");
        }
    }

//    @Override
//    public int deleteLogicById(String pk) {
//        T t =  mapper.selectByPrimaryKey(pk);
//        t.setDeleteFlag(true);
//
//        return mapper.updateByPrimaryKey(t);
//    }
//
//    @Transactional(rollbackFor = {RuntimeException.class})
//    @Override
//    public int deleteLogicBatch(String pks) {
//        int cnt = 0;
//        String[] idArr = pks.split(",");
//        for (String id : idArr) {
//            if (!StringUtils.isEmpty(id)) {
//
//                cnt += deleteLogicById(id);
//            }
//        }
//        return cnt;
//    }


    @Override
    public void deleteByPk(PK pk) {
        int rows = mapper.deleteByPrimaryKey(pk);
        if (rows == 0) {
            throw new DataRetrievalFailureException("No delete for ["
                    + domainType.getTypeName() + "], pk:" + pk);
        }
    }

    @Override
    public int deleteByPks(Collection<PK> pks) {
        Example example = new Example(domainType);
        example.createCriteria().andIn(pkField.getName(), pks);
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByExample(Example example) {
        return mapper.deleteByExample(example);
    }

    private Object _getPkValue(T entity) {
        try {
            pkField.setAccessible(true);
            return pkField.get(entity);
        } catch (IllegalAccessException ex) {
            throw new MyBatisSystemException(ex);
        }
    }

}
