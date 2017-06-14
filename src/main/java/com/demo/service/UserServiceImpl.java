package com.demo.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.demo.mapper.UserMapper;
import com.demo.pojo.User;
import com.github.pagehelper.PageHelper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Autowired
	private UserMapper userMapper;

	// 添加@CacheEvict注解实现缓存删除
	@CacheEvict(value = "userCache", key = "'user.queryAll'")
	@Override
	public List<User> queryUserByName(String name) {
		// 保存数据
		this.redisTemplate.boundValueOps("redis").set("Hello redis !");
		// 设置有效时间为100秒
		this.redisTemplate.boundValueOps("redis").expire(100l, TimeUnit.SECONDS);
		// 给value每次执行加一操作
		this.redisTemplate.boundValueOps("count").increment(1l);
		System.out.println("缓存清理了！");
		List<User> list = this.userMapper.queryUserByName(name);
		return list;
	}

	// 添加@Cacheable注解实现缓存添加
	@Cacheable(value = "userCache", key = "'user.queryAll'")
	// 调用使用UserMapper.xml的Mapper
	@Override
	public List<User> queryAll() {
		System.out.println("从MySQL中查询");
		List<User> list = this.userMapper.queryAll();
		return list;
	}

	// 使用通用Mapper和分页助手
	@Override
	public List<User> queryUserByPage(Integer page, Integer rows) {
		// 设置分页
		PageHelper.startPage(page, rows);
		// 使用通用Mapper的方法进行查询所有数据
		List<User> list = this.userMapper.select(null);
		return list;
	}
}
