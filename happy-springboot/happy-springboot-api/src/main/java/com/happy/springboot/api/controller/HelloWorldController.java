package com.happy.springboot.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloWorldController {

	@RequestMapping("springboot")
	public String helloWorld() {
		return "Hello Springboot";
	}

}
