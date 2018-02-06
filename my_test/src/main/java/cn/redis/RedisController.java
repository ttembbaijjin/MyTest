package cn.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {
	
	@Autowired
	RedisServiceTest redisService;
	
	
	 @RequestMapping(value = "setStr")
	      public String setStr(String key, String val){
	          try {
	             //redisService.setStr(key, val);
	             
	             String str = redisService.getStr(key);
	             System.out.println(str);
	             return "success";
	          } catch (Exception e){
	             e.printStackTrace();
	             return "error";
	         }
	     }
	 

}
