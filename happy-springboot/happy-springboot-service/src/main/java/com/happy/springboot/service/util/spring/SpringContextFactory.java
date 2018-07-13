package com.happy.springboot.service.util.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import java.util.Map;


public class SpringContextFactory {


	public static Object getSpringBean(String beanId) {
		try {
			return GlobalApplicationContext.getInstance().getBean(beanId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static <T> T getSpringBean(Class<T> clazz) {
		try {
			return GlobalApplicationContext.getInstance().getBean(clazz);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static <T> T getSpringBean(String beanName, Class<T> clazz) {
		try {
			return GlobalApplicationContext.getInstance().getBean(beanName, clazz);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static ApplicationContext  getApplication(){
		return GlobalApplicationContext.getInstance().getApplicationContext();
	} 
	 public static <T> Map<String, T> getBeansOfType(Class<T> clazz){
		 return GlobalApplicationContext.getInstance().getBeansOfType(clazz);
		 
	 }

	
	/**手动加载spring上下文
	* @Title: loadClassPath
	* @Description: 
	* @param springContext 
	* @return  void    返回类型
	* @throws
	*/ 
	public static void loadClassPath(String springContext) {
		ApplicationContext context = new ClassPathXmlApplicationContext(springContext);
	}
	
	/**
	 * Servlet可以通过此类,获得Spring上下文对象
	 * @param servletContext
	 * @return
	 */
	public static WebApplicationContext getWebApplicationContext(
			ServletContext servletContext) {
		return WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
	}
	
	



}
