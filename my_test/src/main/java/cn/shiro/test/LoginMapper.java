package cn.shiro.test;

import org.apache.ibatis.annotations.Mapper;

import cn.shiro.pojo.User;

@Mapper
public interface LoginMapper {
	
	int insert(User user);

	User queryUserByUsername(String username);

}
