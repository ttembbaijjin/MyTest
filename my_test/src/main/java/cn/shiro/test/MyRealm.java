package cn.shiro.test;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.shiro.pojo.User;



/**
 * @ClassName: MyRealm 
 * @Description: shiro的realm类
 * @author: zhaojiaxin
 * @date: 2018年1月29日 上午11:18:28
 */
public class MyRealm extends AuthorizingRealm{
	
	/**系统日志*/
	private final Logger LOGGER = LoggerFactory.getLogger(MyRealm.class);
	
	@Autowired
	private LoginService loginService;

	// 授权 权限 角色校验
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	// 认证  是谁  身份校验
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = String.valueOf(token.getPrincipal());
		if (!org.apache.commons.lang3.StringUtils.isBlank(userName)) {
			User user = null;
			try {
				// 先查询用户信息
				 user = loginService.securityToLogin(userName, new String((char[]) token.getCredentials()));
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
			// 查询不到
			if(user == null){
				return null;
			}
			
			if (user != null) {
				// 返回认证后的用户
				AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),
						getName());
				return info;
			}
		}
		return null;
	}

}
