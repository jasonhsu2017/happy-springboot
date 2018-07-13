package com.happy.springboot.service.manager.dyndatasource.interceptor;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMap;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import com.happy.springboot.service.manager.dyndatasource.annotation.DyncTable;
import com.happy.springboot.service.manager.dyndatasource.shardingstage.ITableShardingStrategy;
import com.happy.springboot.service.util.SimpleUtil;
import com.happy.springboot.service.util.spring.ServiceUtil;



// 动态分表插件
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class DyncTableInterceptor implements Interceptor {

	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static final ReflectorFactory DEFAULT_OBJECT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_OBJECT_REFLECTOR_FACTORY);

		Object parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");
		doSplitTable(metaStatementHandler, parameterObject);

		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {

		
	}

	private void doSplitTable(MetaObject metaStatementHandler, Object param) throws Exception {
		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		if (SimpleUtil.isNotBlank(originalSql)) {
			MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
			String id = mappedStatement.getId();
			String className = id.substring(0, id.lastIndexOf("."));
			String methodName = id.substring(id.lastIndexOf(".") + 1);
			Class<?> clazz = Class.forName(className);
			ParameterMap paramMap = mappedStatement.getParameterMap();
			Method method = findMethod(clazz.getDeclaredMethods(), methodName);
		
			DyncTable tableSplit = null;
			if (method != null) {
				tableSplit = method.getAnnotation(DyncTable.class);
			}

			if (tableSplit == null) {
				tableSplit = clazz.getAnnotation(DyncTable.class);
			}

			if (tableSplit != null && SimpleUtil.isNotBlank(tableSplit.strategy())) {
				String convertedSql = "";
				String strategyName = tableSplit.strategy();

				ITableShardingStrategy strategyService=ServiceUtil.getService(strategyName);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put(ITableShardingStrategy.TABLE_NAME, tableSplit.value());
				params.put(ITableShardingStrategy.SPLIT_FIELD, tableSplit.field());
				params.put(ITableShardingStrategy.EXECUTE_PARAM_DECLARE, paramMap);
				params.put(ITableShardingStrategy.EXECUTE_PARAM_VALUES, param);

				convertedSql = originalSql.replaceAll(tableSplit.value(), strategyService.doExecute(params));
				metaStatementHandler.setValue("delegate.boundSql.sql", convertedSql);
			}

		}
	}

	private Method findMethod(Method[] methods, String methodName) {
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		return null;
	}

}
