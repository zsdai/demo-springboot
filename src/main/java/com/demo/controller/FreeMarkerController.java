package com.demo.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("fm")
public class FreeMarkerController {

	@RequestMapping
	public String fm(Map<String, Object> root) {
		root.put("name", "张三");
		root.put("date", new Date());

		return "template";
	}
}
