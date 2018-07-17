package com.happy.springboot.dubbo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;


@SpringBootApplication
@EnableDubboConfiguration()
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
