package com.happy.springboot.service.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringContextAware implements ApplicationContextAware {

	@Override
	public void setApplicationContext(ApplicationContext springContext) throws BeansException {
		GlobalApplicationContext.getInstance().setApplicationContext(springContext);
	}

}
