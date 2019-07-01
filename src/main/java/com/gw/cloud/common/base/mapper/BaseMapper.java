package com.gw.cloud.common.base.mapper;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;
import tk.mybatis.mapper.common.special.InsertUseGeneratedKeysMapper;

public interface BaseMapper<T> extends Mapper<T>, InsertListMapper<T>, InsertUseGeneratedKeysMapper<T> {
}
