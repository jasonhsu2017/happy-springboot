package com.happy.springboot.api;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.happy.springboot.service.manager.dyndatasource.core.DyncDataSourceRegister;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, MybatisAutoConfiguration.class })
@Import(DyncDataSourceRegister.class)
@ServletComponentScan                                       
@ComponentScan(basePackages = { "com.happy.springboot.api","com.happy.springboot.service"})
//@EnableConfigurationProperties
//@ImportResource("classpath:spring-dubbo.xml")
@EnableDubboConfiguration()
@EnableDubbo(scanBasePackages = "com.happy.springboot.service.dubbo.service") 
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
