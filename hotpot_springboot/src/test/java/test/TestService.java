package test;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.enity.User;
import com.test.service.UserService;
import com.test.service.impl.UserServiceImpl;

@SpringBootTest
public class TestService {
	@Autowired
	UserService us;
		
	@Test
	public void selectUser() {
		String user_name = "admin";
		String password = "123456";
		
		User user = us.selectUser(user_name, password);
		
		if(user!=null) {
			System.out.println(user);
		}else {
			System.out.println("null");
		}
	}
	
	@Test
	public void selectOneByid() {
		int id = 1;
		
		User user = us.selectOneByid(id);
		
		if(user!=null) {
			System.out.println(user);
		}else {
			System.out.println("null");
		}
	}
	
}
