package com.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.demo.pojo.User;

@Mapper
public interface UserMapper extends com.github.abel533.mapper.Mapper<User> {

	@Select("select * from user where name like '%${value}%'")
	public List<User> queryUserByName(String name);

	// 使用UserMapper.xml配置文件
	public List<User> queryAll();

}
