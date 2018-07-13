package com.happy.springboot.service.manager.dyndatasource.core;

import java.util.ArrayList;
import java.util.List;

// 动态数据源上下文
public class DyncDataSourceContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static List<String> dataSourceNames = new ArrayList<String>();

	public static void setDataSource(String dataSourceName) {
		contextHolder.set(dataSourceName);
	}

	public static String getDataSource() {
		return contextHolder.get();
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}

	public static boolean containsDataSource(String dataSourceName) {
		return dataSourceNames.contains(dataSourceName);
	}
}
