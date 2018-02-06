package cn.shiro.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import cn.shiro.pojo.User;

@Service
public class LoginServiceImpl implements LoginService {

	/** 系统日志 */
	private final Logger LOGGER = LoggerFactory.getLogger(MyRealm.class);

	@Autowired
	private LoginMapper loginMapper;

	public int create(@RequestBody User user) {

		int insert;
		try {
			insert = loginMapper.insert(user);
			return insert;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}

	/**
	 * 根据用户名查询登录用户信息
	 * 
	 * @author:
	 * @param username,
	 *            password
	 */
	public User securityToLogin(String username, String password) {
		User user = new User();
		try {
			LOGGER.info("根据用户名查询用户: {}", username, password);
			User u = this.queryUserByUsername(username);

			if (u == null) {
				LOGGER.info("用户不存在");
			} else {
				LOGGER.info("根据用户名查询用户成功, username: {}, password: {}", u.getUsername(), u.getPassword());
				user.setPassword(u.getPassword());
				user.setUsername(u.getUsername());
				user.setId(u.getId());

			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("根据用户名查询用户失败");
		}
		return user;
	}

	public User queryUserByUsername(String username) {

		return loginMapper.queryUserByUsername(username);
	}

}
