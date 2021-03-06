package com.gw.cloud.common.base.mapper;


import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.*;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/***
* 目前这个没做什么用，可用来作为自定义查询参考
*/
public class PgSqlProvider extends MapperTemplate {

	public PgSqlProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
		super(mapperClass, mapperHelper);
	}

	/**
	 * 分页查询
	 *
	 * @param ms
	 * @return
	 */
	public SqlNode selectPage(MappedStatement ms) {
		Class<?> entityClass = getEntityClass(ms);
		//修改返回值类型为实体类型
		setResultType(ms, entityClass);

		List<SqlNode> sqlNodes = new ArrayList<>();
		//静态的sql部分:select column ... from table
		sqlNodes.add(new StaticTextSqlNode("SELECT "
				+ EntityHelper.getSelectColumns(entityClass)
				+ " FROM "
				+ tableName(entityClass)));
		//获取全部列
		Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
		List<SqlNode> ifNodes = new ArrayList<>();
		boolean first = true;
		//对所有列循环，生成<if test="property!=null">[AND] column = #{property}</if>
		for (EntityColumn column : columnList) {
			StaticTextSqlNode columnNode
					= new StaticTextSqlNode((first ? "" : " AND ") + column.getColumn() + " = #{entity." + column.getProperty() + "} ");
			if (column.getJavaType().equals(String.class)) {
				ifNodes.add(new IfSqlNode(columnNode, "entity." + column.getProperty() + " != null and " + "entity." + column.getProperty() + " != '' "));
			} else {
				ifNodes.add(new IfSqlNode(columnNode, "entity." + column.getProperty() + " != null "));
			}
			first = false;
		}
		//增加entity判断
		IfSqlNode ifSqlNode = new IfSqlNode(new MixedSqlNode(ifNodes), "entity!=null");
		//将if添加到<where>
		sqlNodes.add(new WhereSqlNode(ms.getConfiguration(), ifSqlNode));
		//处理分页
		sqlNodes.add(new IfSqlNode(new StaticTextSqlNode(" LIMIT #{limit}"), "offset==0"));
		sqlNodes.add(new IfSqlNode(new StaticTextSqlNode(" LIMIT #{limit} OFFSET #{offset} "), "offset>0"));
		return new MixedSqlNode(sqlNodes);
	}
}
