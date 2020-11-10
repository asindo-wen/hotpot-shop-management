package com.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.enity.User;

@Service
public interface UserService {
	User selectUser(String user_name, String password);

	void insertUser(String user_name, String password, int age, String sex,
			String reg_time,String login_time);

	void updateLoginTime(String login_time, int user_id);
	
	void updatePassword(String password, int user_id);

	void updateUser_photo(String user_photo, int user_id);

	void updateUser(String user_name, int age, String sex, int user_id);

	List<User> selectAllUser();

	List<User> selectAll();

	void deleteUser(int user_id);	

	User selectOneByid(int user_id);

	User selectOneByName(String user_name);
}
