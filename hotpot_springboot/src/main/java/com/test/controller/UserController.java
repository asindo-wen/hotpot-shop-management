package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.enity.User;
import com.test.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true")
public class UserController {
	
	@Autowired
	UserService userservice;
	
	// 个人设置
	@RequestMapping("/updateuser")
	@ResponseBody
	public boolean updateUser(String user_name, int age, String sex, int user_id) {
		userservice.updateUser(user_name,age,sex,user_id);
		return true;
	}

	// 修改密码
	@PostMapping("/updatepassword")
	@ResponseBody
	public boolean updatePassword(String oldpassword,String password, int user_id)  {
		User user = userservice.selectOneByid(user_id);

		if (user.getPassword().equals(oldpassword)) {
			userservice.updatePassword(password, user_id);
			return true;
		} else {
			 return false;
		}
	}
	
	// 上传头像
	@RequestMapping("/updateuser_photo")
	@ResponseBody
	public boolean updateuser_photo(String user_photo, int user_id) {
		userservice.updateUser_photo(user_photo,user_id);
		return true;
	}

	
	
	@GetMapping("/selectonebyid")
	@ResponseBody
	public User selectOneByid(int user_id) {
		User user = userservice.selectOneByid(user_id);
		return user;
	}

	@GetMapping("/selectonebyname")
	@ResponseBody
	public User selectOne(String user_name) {
		User user = userservice.selectOneByName(user_name);
		return user;
	}
}
