package cn.optimisticLock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {

	@Autowired
	private MyMapper myMapper;

	@Test
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void updateUser() {
		User user = new User();
		user.setUid("3");
		for (int i = 100; i < 200; i++) {
			User user2 = myMapper.getUserById(user);
			System.out.println(user2);
			String s = "a" + i;
			user2.setUname(s);
			int j = myMapper.updateUser(user2);
			System.out.println(j);
		}
	}
	
	
	public void test2(){
		
	}

}
