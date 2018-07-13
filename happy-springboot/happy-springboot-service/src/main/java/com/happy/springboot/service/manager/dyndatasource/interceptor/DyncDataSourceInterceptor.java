package com.happy.springboot.service.manager.dyndatasource.interceptor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.happy.springboot.service.manager.dyndatasource.annotation.DBShardingBizFiled;
import com.happy.springboot.service.manager.dyndatasource.annotation.TargetDataSource;
import com.happy.springboot.service.manager.dyndatasource.core.DyncDataSourceContextHolder;
import com.happy.springboot.service.manager.dyndatasource.shardingstage.IDbShardingStrategy;
import com.happy.springboot.service.util.spring.ServiceUtil;

/**
 * 
 * @author jasoHsu
 * 动态数据源在切换
 */
@Component
@Order(-10)
@Aspect
public class DyncDataSourceInterceptor {

	@Before("@annotation(targetDataSource)")
	public void changeDataSource(JoinPoint point, TargetDataSource targetDataSource) throws Exception {
		Object[] args = point.getArgs();
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		String dbIndx = null;
		if (targetDataSource.isSharding()) {
			Parameter[] parameters = method.getParameters();
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					if (parameters[i].isAnnotationPresent(DBShardingBizFiled.class)) {
						long shardingBizKey = (long) args[i];
						//获取分库策略
						IDbShardingStrategy strategyService=ServiceUtil.getService(targetDataSource.strategy());
						dbIndx=strategyService.doSharding(shardingBizKey);
					}
				}
			}
		}
 		String targetDataSourceName = targetDataSource.value() + (dbIndx == null ? "" : dbIndx);
 		if (DyncDataSourceContextHolder.containsDataSource(targetDataSourceName)) {
			DyncDataSourceContextHolder.setDataSource(targetDataSourceName);
		}
	}

	@After("@annotation(targetDataSource)")
	public void resetDataSource(JoinPoint point, TargetDataSource targetDataSource) {
		DyncDataSourceContextHolder.clearDataSource();
	}
}
