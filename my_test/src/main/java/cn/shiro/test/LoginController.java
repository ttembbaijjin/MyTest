package cn.shiro.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/test")
public class LoginController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	/**您没有得到相应的授权*/
	private final String UNAUTHORIZED = "您没有得到相应的授权！";
	/**帐号不存在*/
	private final String NO_USER_WITH_USERNAME = "帐号不存在. There is no user with username of " ;
	/**帐号已过期*/
	private final String ACCOUNT_WAS_EXPIRED = "帐号已过期. the account for username %s was expired.";
	/**帐号已被禁用*/
	private final String ACCOUNT_WAS_DISABLED = "帐号已被禁用. The account for username %s was disabled.";
	/**
	 * 账户已锁定
	 */
	private final String ACCOUNT_WAS_LOCKED = "帐号已被锁定. The account for username %s was locked.";
	/**
	 * 登录密码错误
	 */
	private final String PASSWORD_WAS_INCORRECT = "登录密码错误. Password for account %s was incorrect.";
	/** 登录失败次数过多 */
	private final String LOGIN_TOO_MANY= "登录失败次数过多";
	/**登录成功信息*/
	private final String LOGIN_SUCCESS_MESSAGE = "登录成功！";
	/**
	 * success : true
	 */
	private final String SUCCESS_TRUE = "true";
	/**
	 * success : false
	 */
	private final String SUCCESS_FALSE = "false";
	/**
	 * 空字符串
	 */
	public final String EMPTY_STRING = "";
	
	@Autowired
	private LoginService loginService;
	
	
	@RequestMapping(value="/main")
	public ModelAndView toMain(){
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(String username, String password, 
			HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("login");
		String msg = "";
		if (StringUtils.isNotBlank(username)&&StringUtils.isNotBlank(password)){
			// token.setRememberMe(false);
			String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			try {
				subject.login(token);
				if(subject.isAuthenticated()){
					subject.hasRole(this.EMPTY_STRING);
					return new ModelAndView("show");
				}
				msg = this.LOGIN_SUCCESS_MESSAGE;
				view.addObject("success", this.SUCCESS_TRUE);
			}catch (IncorrectCredentialsException e) {
				msg = String.format(this.PASSWORD_WAS_INCORRECT, token.getPrincipal());
		        view.addObject("success", this.SUCCESS_FALSE);
			}catch (ExcessiveAttemptsException e) {
		        msg = this.LOGIN_TOO_MANY;
		        view.addObject("success", this.SUCCESS_FALSE);
		    } catch (LockedAccountException e) {
		        msg = String.format(this.ACCOUNT_WAS_LOCKED, token.getPrincipal());
		        view.addObject("success", this.SUCCESS_FALSE);
		    } catch (DisabledAccountException e) {
		        msg = String.format(this.ACCOUNT_WAS_DISABLED, token.getPrincipal());
		        view.addObject("success", this.SUCCESS_FALSE);
		    } catch (ExpiredCredentialsException e) {
		        msg = String.format(this.ACCOUNT_WAS_EXPIRED, token.getPrincipal());
		        view.addObject("success", this.SUCCESS_FALSE);
		    } catch (UnknownAccountException e) {
		        msg = this.NO_USER_WITH_USERNAME + token.getPrincipal();
		        view.addObject("success", this.SUCCESS_FALSE);
		    } catch (UnauthorizedException e) {
		        msg = this.UNAUTHORIZED + e.getMessage();
		        view.addObject("success", this.SUCCESS_FALSE);
		    }
		}else{
			msg = "用户名或密码不能为空！";
			view.addObject("success", this.SUCCESS_FALSE);
		}
		return view;
		
	}

}
