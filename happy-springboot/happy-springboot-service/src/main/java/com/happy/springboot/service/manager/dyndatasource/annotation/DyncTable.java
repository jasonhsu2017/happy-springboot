package com.happy.springboot.service.manager.dyndatasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// 动态分表注解
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface DyncTable {
	// 表名
	public String value();

	// 分表字段
	public String field() default "";

	// 获取分表策略
	public String strategy();
}
