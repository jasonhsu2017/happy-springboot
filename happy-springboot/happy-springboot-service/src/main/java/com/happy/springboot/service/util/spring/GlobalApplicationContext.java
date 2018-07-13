package com.happy.springboot.service.util.spring;
import org.springframework.context.ApplicationContext;

import java.util.Map;


public class GlobalApplicationContext  {
	
	private ApplicationContext applicationContext ;

	private static class InnerClass{
		//内部类的实例与外部类的实例没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载
		private static GlobalApplicationContext context =  new GlobalApplicationContext();	
	}
	
	private GlobalApplicationContext() {
	}

	public void setApplicationContext(ApplicationContext springContext) {
		
		applicationContext = springContext;
	}

	
	
	/**
	 * 单例对象获取方法
	 * 
	 * @return
	 */
	public static GlobalApplicationContext getInstance() {
		return InnerClass.context;
	}
	public  Object getBean(String beanId) {
		return applicationContext.getBean(beanId);
	}

	public  <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
	

	public  <T> T getBean(String beanName, Class<T> clazz) {
		return applicationContext.getBean(beanName, clazz);
		
		
	
	}

	public <T> Map<String, T> getBeansOfType(Class<T> clazz){
		
		return applicationContext.getBeansOfType(clazz);
	}
	

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public String getProperty(String name){
		return applicationContext.getEnvironment().getProperty(name);
	}
}

