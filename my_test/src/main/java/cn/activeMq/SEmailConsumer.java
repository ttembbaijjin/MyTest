package cn.activeMq;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

import cn.javamail.MailUtils;

/**
 * @ClassName: SEmailConsumer 
 * @Description: 发送邮件mq消息的消费者
 * @author: zhaojiaxin
 * @date: 2018年1月5日 下午3:43:38
 */
@Component("sEmailConsumer")
public class SEmailConsumer implements MessageListener{
	
	public void onMessage(Message message) {
		MapMessage mapMessage = (MapMessage) message;
		try {
			String emailAddress = mapMessage.getString("emailAddress");
			String content = mapMessage.getString("emailContent");
			MailUtils.sendMail("速运快递激活邮件", content, emailAddress);
			System.out.println("mq发送邮件成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
