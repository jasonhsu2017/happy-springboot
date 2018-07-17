package com.happy.springboot.dubbo.consumer.service;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.happy.springboot.dubbo.contract.service.IHelloWorldDubboService;

@Component
public class HelloWorldConsumerService {
	  @Reference(interfaceClass=IHelloWorldDubboService.class)
	  private IHelloWorldDubboService helloService;
	  
	  public String sayHello(){
		  return helloService.sayHello();
	  }
	  
}
