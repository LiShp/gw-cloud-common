package com.gw.cloud.common.base.entity;


import java.io.Serializable;

/**
 * super class to define a entity
 *
 * @param <ID> 主键类型
 */
public abstract class BaseEntity<ID> implements Serializable {

	private static final long serialVersionUID = -8075827049184773786L;

	public abstract int hashCode();

	public abstract boolean equals(Object obj);
}

