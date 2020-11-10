package com.test.enity;

public class User {
	private int user_id;
	private String user_name;
	private String password;
	private int age;
	private String sex;
	private String user_photo;//用户头像
	private int user_type;//用户类型	
    
	private String reg_time; //注册时间   
	private String login_time;//登陆时间
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUser_photo() {
		return user_photo;
	}
	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public String getReg_time() {
		return reg_time;
	}
	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	public User(int user_id, String user_name, String password, int age, String sex, String user_photo, int user_type,
			String reg_time, String login_time) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.age = age;
		this.sex = sex;
		this.user_photo = user_photo;
		this.user_type = user_type;
		this.reg_time = reg_time;
		this.login_time = login_time;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", password=" + password + ", age=" + age
				+ ", sex=" + sex + ", user_photo=" + user_photo + ", user_type=" + user_type + ", reg_time=" + reg_time
				+ ", login_time=" + login_time + "]";
	}
	
	
	
	

}
