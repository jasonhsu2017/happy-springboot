package com.happy.springboot.service.util.spring;

import java.util.Map;

public class ServiceUtil {
    @SuppressWarnings({  "unchecked" })
	public static <T> T getService(String beanId) {
        return (T) SpringContextFactory.getSpringBean(beanId);
    }
    
  	public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
          return  SpringContextFactory.getBeansOfType(clazz);
      }

}
