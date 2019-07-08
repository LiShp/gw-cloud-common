package com.gw.cloud.common.base.entity;//package cn.jboost.springboot.parent.domain;

import javax.persistence.Id;
import java.util.Objects;

/**
 * 非自增主键场景
 * @param <ID>
 */
public abstract class BaseDomain<ID> extends BaseEntity<ID> {
	@Id
	protected ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o){ return true;}
		if (o == null || getClass() != o.getClass()) {return false;}
		BaseDomain<?> that = (BaseDomain<?>) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
