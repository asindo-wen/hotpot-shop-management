package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.enity.User;
import com.test.mapper.UserMapper;
import com.test.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper usermapper;

	@Override
	public User selectUser(String user_name, String password) {
		User user = usermapper.selectUser(user_name, password);
		return user;
	}

	@Override
	public void insertUser(String user_name,String password,int age,String sex,
			String reg_time,String login_time) {
		usermapper.insertUser(user_name,password,age,sex,reg_time,login_time);
	}

	@Override
	public void updateLoginTime(String loginTime, int id) {
		usermapper.updateLoginTime(loginTime, id);
		
	}

	@Override
	public void updateUser_photo(String user_photo, int user_id) {
		usermapper.updateregPhoto(user_photo, user_id);
		
	}

	@Override
	public void updateUser(String user_name, int age, String sex, int user_id) {	
		usermapper.updateUser(user_name, age, sex, user_id);	
	}

	@Override
	public List<User> selectAllUser() {
		 List<User> list = usermapper.selectAllUser();
		return list;
	}

	@Override
	public void deleteUser(int id) {
		usermapper.deleteUser(id);	
	}

	@Override
	public void updatePassword(String password, int user_id) {
      usermapper.updatePassword(password, user_id);		
	}

	@Override
	public User selectOneByid(int id) {
		User user = usermapper.selectOneByid(id);
		return user;
	}

	@Override
	public List<User> selectAll() {
		
		return usermapper.selectAll();
	}

	@Override
	public User selectOneByName(String user_name) {
		 User user = usermapper.selectOneByName(user_name);
		return user;
	}


}
