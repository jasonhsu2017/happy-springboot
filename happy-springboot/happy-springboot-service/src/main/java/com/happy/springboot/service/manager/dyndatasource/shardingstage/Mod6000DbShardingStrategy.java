package com.happy.springboot.service.manager.dyndatasource.shardingstage;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.happy.springboot.service.util.SimpleUtil;


@Service("db_sharding_6000")
public class Mod6000DbShardingStrategy implements IDbShardingStrategy,EnvironmentAware {

	private Mod6000DbConfig mod6000DbConfig;

	@Override
	public String doSharding(long routeId) throws Exception {
		Long index = (routeId % mod6000DbConfig.getTotalTable())
				/ (mod6000DbConfig.getTotalTable() / mod6000DbConfig.getTotalDb()) + mod6000DbConfig.getStart();
		return SimpleUtil.lpad(index, mod6000DbConfig.getNumLen(), '0');
	}

	@Override
	public void setEnvironment(Environment environment) {
		initDefaultDataSource(environment);
	}

	private void initDefaultDataSource(Environment env) {
    	Mod6000DbConfig config=new Mod6000DbConfig();
        // 配置参数
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "dbShardingStrategy.mod6000.");
        config.setTotalTable(Integer.valueOf(propertyResolver.getProperty("totalTable")));
        config.setNumLen(Integer.valueOf(propertyResolver.getProperty("numLen")));
        config.setStart(Integer.valueOf(propertyResolver.getProperty("start")));
        config.setTotalDb(Integer.valueOf(propertyResolver.getProperty("totalDb"))); 
        this.mod6000DbConfig=config;
    }

}
