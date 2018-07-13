package com.happy.springboot.service.manager.dyndatasource.core;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DyncRouteDataSource extends AbstractRoutingDataSource {
@Override
	protected Object determineCurrentLookupKey() {
		return DyncDataSourceContextHolder.getDataSource();
	}
	public DataSource findTargetDataSource() {
		return this.determineTargetDataSource();
	}
}
