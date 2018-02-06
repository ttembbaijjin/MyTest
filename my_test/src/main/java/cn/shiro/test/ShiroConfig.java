package cn.shiro.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	
	/**系统日志*/
	private static Logger LOGGER = LoggerFactory.getLogger(ShiroConfig.class);
	
	@Bean(name = "shiroRealm")
    public MyRealm shiroRealm() {  
		MyRealm realm = new MyRealm(); 
        return realm;
    }
	
	@Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
	
	// 开启shiro的注解模式
	@Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }
	
	/**
	 * 安全管理器 
	 * @return
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm shiroRealm){
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(shiroRealm);
		return manager;
	}
	
	 @Bean
	 public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
	 }
	 
	 /**
		 *  Shiro 的 Web 过滤器 
		 * @return
		 */
		@Bean(name = "shiroFilter")
		public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
			ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
			bean.setSecurityManager(securityManager);
			bean.setLoginUrl("/main");
			//用户访问未对其授权的资源时，所显示的连接
			bean.setUnauthorizedUrl("/login");
			Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
			LOGGER.info("##################从数据库读取权限规则，加载到shiroFilter中##################");
			//filterChainDefinitionMap.put("/", "anon");//anon 可以理解为不拦截
			filterChainDefinitionMap.put("/test/**", "anon");
			// 下面这个必须要放行,不然登录的controller会请求不到
			filterChainDefinitionMap.put(" /test/** ", "anon");
			filterChainDefinitionMap.put(" /login.ftl* ", "anon");
			filterChainDefinitionMap.put("/show.ftl* ","authc");
			//反爬虫协议
			filterChainDefinitionMap.put("/robots.txt", "anon");
//			filterChainDefinitionMap.put("/css/**", "anon");
//			filterChainDefinitionMap.put("/fonts/**", "anon");
//			filterChainDefinitionMap.put("/img/**", "anon");
			filterChainDefinitionMap.put("/js/**", "anon");
			filterChainDefinitionMap.put("/**", "authc");
			bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
			return bean;
		}

}
