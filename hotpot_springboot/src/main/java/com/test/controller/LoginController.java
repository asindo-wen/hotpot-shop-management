package com.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.enity.User;
import com.test.service.UserService;

@RestController
@RequestMapping("/Login")
@CrossOrigin(allowCredentials = "true")
public class LoginController {
	@Autowired
	UserService userservice;

	// 登陆
	@PostMapping("/login")
	@ResponseBody
	public User login(String user_name, String password) throws Exception {
		
		if(userservice.selectOneByName(user_name)==null) {
			 throw new Exception("账号或密码错误");
		}else {
			User user = userservice.selectUser(user_name, password);
			if(user==null) {
				 throw new Exception("账号或密码错误");
			}else {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				user.setLogin_time(df.format(new Date()));
				userservice.updateLoginTime(user.getLogin_time(), user.getUser_id());
				return user;
			}
		}
	}

	// 注册
	@RequestMapping("/insertUser")
	@ResponseBody
	public boolean insertUser(String user_name, String password, String confirm, int age,
			String sex) {
		User user = userservice.selectOneByName(user_name);
		if (user.getUser_name().equals(user_name)) {
			return false;
		} else {
			if (password.equals(confirm)) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				String reg_time = df.format(new Date());
				userservice.insertUser(user_name, password, age, sex, reg_time, reg_time);
				return true;
			} else {
				return false;
			}
		}
	}

}
