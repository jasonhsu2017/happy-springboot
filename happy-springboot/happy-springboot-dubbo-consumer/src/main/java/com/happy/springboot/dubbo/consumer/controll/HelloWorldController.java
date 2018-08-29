package com.happy.springboot.dubbo.consumer.controll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happy.springboot.dubbo.consumer.service.HelloWorldConsumerService;

@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @Autowired
    private HelloWorldConsumerService HelloWorldConsumerService;

    @RequestMapping("springboot")
    public String helloWorld() {
       //ok
        return HelloWorldConsumerService.sayHello();
    }

}
