package com.demo.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@Resource
	private Environment environment;
	
	@Value("${name}")
	private String name;
	
	@Value("${url}")
	private String url;

	@RequestMapping("info")
	public String info() {
		System.out.println(environment.getProperty("name"));
		System.out.println(environment.getProperty("url"));
		System.out.println("方式二获取:"+name);
		System.out.println(url);
		return "Hello world!";
	}

}
