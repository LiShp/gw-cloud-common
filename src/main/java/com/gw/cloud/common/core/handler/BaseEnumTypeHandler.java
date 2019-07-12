package com.gw.cloud.common.core.handler;

import com.gw.cloud.common.core.base.enums.BaseEnum;
import com.gw.cloud.common.core.base.exception.ApplicationException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * 枚举类型转换器
 *
 * @author WRQ
 * @date 2019/6/25
 * @since 1.0.0
 */
public class BaseEnumTypeHandler<E extends Enum<E> & BaseEnum> extends BaseTypeHandler<E> {

    /**
     * 枚举类的类型
     */
    private Class<E> type;
    /**
     * 枚举类的内容
     */
    private E[] enums;

    /**
     * 设置配置文件或注解中要转换的枚举类以及枚举类的内容
     *
     * @param type 配置文件或注解中设置的要转换的枚举类
     */
    public BaseEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null.");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setObject(i, parameter.getId());
        } else {
            ps.setObject(i, parameter.getId(), jdbcType.TYPE_CODE);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String id = rs.getString(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(id);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String id = rs.getString(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(id);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String id = cs.getString(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(id);
        }
    }

    /**
     * 遍历枚举类型转换（将取出来的值全部转换成字符串进行比较）
     *
     * @param id 转换成字符串类型的枚举id属性
     * @return id属性对应的枚举
     */
    private E locateEnumStatus(String id) {
        if (id == null) {
            return null;
        }
        for (E e : enums) {
            if (Objects.toString(e.getId()).equals(id)) {
                return e;
            }
        }
        throw new ApplicationException("Unknown enum id：" + id + ",please check enum type:" + type.getSimpleName() + ".");
    }
}
