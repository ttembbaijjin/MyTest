package cn.restTemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	/**
	 * @FunctionName: restTemplate
	 * @Description: rest接口调用工具类对象注入
	 * @author:
	 * @date: 2017年3月1日 下午5:46:18
	 * @return RestTemplate
	 */
	@Bean
	RestTemplate restTemplate() {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setConnectionRequestTimeout(30000);
		requestFactory.setConnectTimeout(30000);
		requestFactory.setReadTimeout(30000);
		return new RestTemplate(requestFactory);
	}
}
