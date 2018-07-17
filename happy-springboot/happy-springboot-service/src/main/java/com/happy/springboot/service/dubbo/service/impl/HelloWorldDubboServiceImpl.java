package com.happy.springboot.service.dubbo.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.happy.springboot.service.dubbo.service.IHelloWorldDubboService;


@Service(interfaceClass = IHelloWorldDubboService.class)
public class HelloWorldDubboServiceImpl implements IHelloWorldDubboService {
	@Override
	public String sayHello() {
		return "hello world";
	}
  
}
