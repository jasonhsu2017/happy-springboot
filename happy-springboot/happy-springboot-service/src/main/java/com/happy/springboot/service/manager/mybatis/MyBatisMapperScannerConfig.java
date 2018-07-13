package com.happy.springboot.service.manager.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(DataSourceConfiguration.class)
public class MyBatisMapperScannerConfig {

	/**
	 * 注入mapperScannerConfigurer
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "mapperScannerConfigurer")
	public MapperScannerConfigurer getMapperScannerConfigurer() throws Exception {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.happy.springboot.service.dao");
		mapperScannerConfigurer.setAnnotationClass(MyBatisRepository.class);

		return mapperScannerConfigurer;
	}
}
