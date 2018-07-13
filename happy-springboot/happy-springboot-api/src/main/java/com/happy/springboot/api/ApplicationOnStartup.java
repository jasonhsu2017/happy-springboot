package com.happy.springboot.api;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.happy.springboot.service.util.spring.GlobalApplicationContext;


public class ApplicationOnStartup implements ApplicationListener<ContextRefreshedEvent> {
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
        GlobalApplicationContext.getInstance().setApplicationContext(event.getApplicationContext());
	}

}
