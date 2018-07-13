package com.happy.springboot.service.manager.dyndatasource.shardingstage;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.happy.springboot.service.util.SimpleUtil;

// 以6000取模策略
@Service("table_sharding_6000")
public class Mod6000TableShardingStrategy implements ITableShardingStrategy, EnvironmentAware {

	private Mod6000TableConfig mod6000TableConfig;

	@SuppressWarnings("rawtypes")
	@Override
	public String doExecute(Map<String, Object> params) throws Exception {
		StringBuilder sb = new StringBuilder(params.get(ITableShardingStrategy.TABLE_NAME).toString());
		String field = (String) params.get(ITableShardingStrategy.SPLIT_FIELD);
		Object paramValue = params.get(ITableShardingStrategy.EXECUTE_PARAM_VALUES);
		String value = "";
		if (paramValue instanceof Map) {
			value = String.valueOf(((Map) paramValue).get(field));
		} else {
			value = BeanUtils.getProperty(paramValue, field);
		}
		String investmentSuffix = SimpleUtil.investmentSuffix(Long.valueOf(value),
				mod6000TableConfig.getTotalTable(), mod6000TableConfig.getNumLen(), mod6000TableConfig.getStart());
		return sb.append(investmentSuffix).toString();
	}

	@Override
	public void setEnvironment(Environment environment) {

		Mod6000TableConfig config = new Mod6000TableConfig();
		// 配置参数
		RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(environment,"tableShardingStrategy.mod6000.");
		config.setTotalTable(Integer.valueOf(propertyResolver.getProperty("totalTable")));
		config.setNumLen(Integer.valueOf(propertyResolver.getProperty("numLen")));
		config.setStart(Integer.valueOf(propertyResolver.getProperty("start")));
		this.mod6000TableConfig = config;

	}
}
