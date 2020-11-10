package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.enity.User;

@Mapper
public interface UserMapper {
	
	//验证登录
	@Select("SELECT * FROM `user` WHERE user_name=#{user_name} AND password=#{password}")
	User selectUser(@Param("user_name")String user_name, @Param("password")String password);
    	
	// 注册管理员
	@Insert("insert into user value(null,#{user_name},#{password},#{age},#{sex},null,2,#{reg_time},#{login_time})")
	void insertUser(@Param("user_name") String user_name, @Param("password") String password, 
			@Param("age")int age, @Param("sex")String sex, @Param("reg_time") String reg_time,
			@Param("login_time") String login_time);

	// 更改登陆时间
	@Update("UPDATE `user` SET user.login_time =#{login_time} WHERE user.user_id=#{user_id}")
	void updateLoginTime(@Param("login_time") String login_time, @Param("user_id") int user_id);

	// 判断管理员名是否重复
	@Select("SELECT * FROM `user` WHERE user_name=#{user_name}")
	User selectOneUser(@Param("user_name") String user_name);

	// 更改密码
	@Update("update `user` SET `password`=#{password} WHERE user_id=#{user_id}")
	void updatePassword(@Param("password") String password, @Param("user_id") int user_id);

	// 更改头像
	@Update("update `user` SET `user_photo`=#{user_photo} WHERE user_id=#{user_id}")
	void updateregPhoto(@Param("user_photo") String user_photo, @Param("user_id") int user_id);

	//根据id查询管理员
	@Select("SELECT * FROM `user` WHERE user_id=#{user_id}")
	User selectOneByid(@Param("user_id") int user_id);
	
	//根据管理员名查询管理员
	@Select("SELECT * FROM `user` WHERE user_name=#{user_name}")
	User selectOneByName(@Param("user_name") String user_name);
	
	// 管理员删除管理员
	@Delete("DELETE FROM `user` WHERE user_id=#{user_id}")
	void deleteUser(@Param("user_id") int user_id);

	// 个人设置
	@Update("update `user` SET `user_name`=#{user_name},age=#{age},sex=#{sex} WHERE user_id=#{user_id}")
	void updateUser(@Param("user_name") String user_name, @Param("age") int age, @Param("sex") String sex, 
			 @Param("user_id") int user_id);

	// 管理员查看管理员
	@Select("SELECT * FROM user WHERE user_type=2")
	List<User> selectAllUser();
	
	//查询所有管理员与用户
	@Select("SELECT * FROM user")
	List<User> selectAll();	

}
