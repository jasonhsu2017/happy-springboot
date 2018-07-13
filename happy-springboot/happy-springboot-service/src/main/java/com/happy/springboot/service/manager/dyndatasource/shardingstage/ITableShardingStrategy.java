package com.happy.springboot.service.manager.dyndatasource.shardingstage;

import java.util.Map;

public interface ITableShardingStrategy {

	public static final String TABLE_NAME = "table_name";
	public static final String SPLIT_FIELD = "split_field";
	public static final String EXECUTE_PARAM_DECLARE = "execute_param_declare";
	public static final String EXECUTE_PARAM_VALUES = "execute_param_values";
	
	
	public String doExecute(Map<String, Object> params) throws Exception;
}
