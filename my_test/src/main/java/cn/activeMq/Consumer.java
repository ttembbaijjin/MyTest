package cn.activeMq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component  
public class Consumer {
	
	 // 消息消费者的类上必须加上@Component，或者是@Service，这样的话，消息消费者类就会被委派给Listener类
	 // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息  
    @JmsListener(destination = "mytest.queue")  
    public void receiveQueue(String text) {  
        System.out.println("Consumer收到的报文为:"+text);  
    }  

}
