package com.happy.springboot.service.manager.mybatis;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.pool.DruidDataSource;
import com.happy.springboot.service.exception.BizException;
import com.happy.springboot.service.manager.dyndatasource.core.DyncRouteDataSource;
import com.happy.springboot.service.manager.dyndatasource.interceptor.DyncTableInterceptor;


@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DataSourceConfiguration implements TransactionManagementConfigurer {

	@Resource
	DyncRouteDataSource dynamicRouteDataSource;

	
	@Bean(name = "masterDataSource", initMethod = "init", destroyMethod = "close")
	@Qualifier("masterDataSource")
	@ConfigurationProperties(prefix = "spring.dataSource.happyboot")
	public DataSource getMasterDataSource() {
		return createDefaultDruidDataSource();
	}

	public DruidDataSource createDefaultDruidDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setMaxWait(60000l);
		druidDataSource.setMaxActive(20);
		druidDataSource.setInitialSize(1);
		druidDataSource.setMinIdle(1);
		druidDataSource.setTimeBetweenEvictionRunsMillis(3000l);
		druidDataSource.setMinEvictableIdleTimeMillis(300000l);
		druidDataSource.setConnectionProperties("druid.stat.slowSqlMillis=3000");
		druidDataSource.setValidationQuery("SELECT 'x'");
		druidDataSource.setTestWhileIdle(true);
		druidDataSource.setTestOnBorrow(false);
		druidDataSource.setTestOnReturn(false);
		try {
			druidDataSource.setFilters("stat");
		} catch (SQLException e) {
			throw new BizException("create dataSource error", e);
		}
		return druidDataSource;
	}
	
	
	
	
	/**
	 * 注入sqlSessionFactory
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory getinvestListingSqlSessionFactory() throws Exception {
		String configLocation = "classpath:/conf/mybatis/configuration.xml";
		String mapperLocation = "classpath*:/com/happy/springboot/service/dao/*/*Mapper.xml";
		SqlSessionFactory sqlSessionFactory = createDefaultSqlSessionFactory(getMasterDataSource(), configLocation,
				mapperLocation);

		return sqlSessionFactory;
	}

	/**
	 * 创建sqlSessionFactory
	 * 
	 * @param dataSource
	 * @param configLocation
	 * @param mapperLocation
	 * @return
	 * @throws Exception
	 */
	public SqlSessionFactory createDefaultSqlSessionFactory(DataSource dataSource, String configLocation,
			String mapperLocation) throws Exception {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setTypeAliasesPackage("com.happy.springboot.service.dao.model.entity");
		factory.setDataSource(dataSource);
		//Interceptor[] inters = new Interceptor[1];
		//inters[0] = new DyncTableInterceptor();
		//factory.setPlugins(inters);

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		// factory.setConfigLocation(resolver.getResource(configLocation));
		SqlSessionFactory target = null;
		try {
			factory.setMapperLocations(resolver.getResources(mapperLocation));
			target = (SqlSessionFactory) factory.getObject();
			target.getConfiguration().setDefaultStatementTimeout(30);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return target;
	}

	/**
	 * 事务管理
	 * 
	 * @return
	 */
	@Bean(name = "txManager")
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(getMasterDataSource());
	}


	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return txManager();
	}

}
