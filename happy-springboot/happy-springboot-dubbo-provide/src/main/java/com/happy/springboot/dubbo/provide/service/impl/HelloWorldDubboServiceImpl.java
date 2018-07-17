package com.happy.springboot.dubbo.provide.service.impl;


import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.happy.springboot.dubbo.contract.service.IHelloWorldDubboService;


@Service(interfaceClass = IHelloWorldDubboService.class)
@Component
public class HelloWorldDubboServiceImpl implements IHelloWorldDubboService {
	@Override
	public String sayHello() {
		return "hello world";
	}
  
}
