package com.test.enity;

public class StaffSalary {
	private int id;
	private int staff_id;
	private int salary_id;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public int getSalary_id() {
		return salary_id;
	}
	public void setSalary_id(int salary_id) {
		this.salary_id = salary_id;
	}
	public StaffSalary(int staff_id, int salary_id) {
		super();
		this.staff_id = staff_id;
		this.salary_id = salary_id;
	}
	
	
	
	
	
}
