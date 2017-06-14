package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.pojo.User;
import com.demo.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("list/{name}")
	public List<User> queryUserByName(@PathVariable String name) {
		List<User> list = this.userService.queryUserByName(name);
		return list;
	}
	@RequestMapping("list")
	public List<User> queryAll() {
		List<User> list = this.userService.queryAll();
		return list;
	}

	@RequestMapping("list/{page}/{rows}")
	public List<User> queryUserAll(@PathVariable Integer page, @PathVariable Integer rows) {
		List<User> list = this.userService.queryUserByPage(page, rows);
		return list;
	}
}