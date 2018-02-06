package cn.shiro.test;

import org.springframework.web.bind.annotation.RequestBody;

import cn.shiro.pojo.User;

public interface LoginService {
	
	int create(@RequestBody User user);
	
	User securityToLogin(String username, String password);
	
	User queryUserByUsername(String username);

}
