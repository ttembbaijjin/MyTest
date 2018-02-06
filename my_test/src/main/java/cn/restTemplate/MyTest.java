package cn.restTemplate;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.opg.uop.point.common.api.request.GetPointByMemberIdReq;
import com.opg.uop.point.common.api.result.GetPointByMemberIdRes;
import com.opg.uop.sms.common.api.request.MessageForUopPassportRequest;
import com.opg.uop.sms.common.api.result.MessageForUopPassportResult;

@Controller
@RequestMapping
public class MyTest {
	
	@Autowired
    private RestTemplate restTemplate;

	private static final String URL = "http://localhost:10044/points/getPointByMemberId";

	@RequestMapping("test")
	@ResponseBody
	public GetPointByMemberIdRes test(@RequestBody GetPointByMemberIdReq req) {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<GetPointByMemberIdReq> entity = new HttpEntity<GetPointByMemberIdReq>(req,headers);
		GetPointByMemberIdRes res = restTemplate.postForEntity(URL, entity, GetPointByMemberIdRes.class).getBody();
		System.out.println(res.getMemberId());
		return res;
	}
	
	@RequestMapping("testSms")
	@ResponseBody
	public MessageForUopPassportResult testSms(@RequestBody MessageForUopPassportRequest req) {
		String url = "http://10.206.30.169:20002/sms/sendMessage";
		Map<String,String> templateMap = new HashMap<String, String>();
		templateMap.put("verifyCode", "111");
		req.setTemplateMap(templateMap);
		req.setPhone("15234832668");
		req.setTemplateId(5);
		MessageForUopPassportResult res = restTemplate.postForEntity(url, req, MessageForUopPassportResult.class).getBody();
		return res;
	}

}
