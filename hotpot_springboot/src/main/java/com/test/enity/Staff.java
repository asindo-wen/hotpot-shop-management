package com.test.enity;

public class Staff {
	private int staff_id;
	private String staff_name;
	private int age;
	private String sex;
	private long phone;
	private String regTime;
	
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
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
	
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Staff(int staff_id, String staff_name, int age, String sex, long phone, String regTime) {
		super();
		this.staff_id = staff_id;
		this.staff_name = staff_name;
		this.age = age;
		this.sex = sex;
		this.phone = phone;
		this.regTime = regTime;
	}
	@Override
	public String toString() {
		return "Staff [staff_id=" + staff_id + ", staff_name=" + staff_name + ", age=" + age + ", sex=" + sex
				+ ", phone=" + phone + ", regTime=" + regTime + "]";
	}

	

}