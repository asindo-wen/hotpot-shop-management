package com.test.enity;

public class Salary {
	private int salary_id;
	private int staff_id;
	private String staff_name;
	private int basicSalary;
	private int bonus;
	private int allSalary;
	private String creTime;

	public int getSalary_id() {
		return salary_id;
	}
	public void setSalary_id(int salary_id) {
		this.salary_id = salary_id;
	}
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
	public int getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(int basicSalary) {
		this.basicSalary = basicSalary;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public int getAllSalary() {
		return allSalary;
	}
	public void setAllSalary(int allSalary) {
		this.allSalary = allSalary;
	}
	
	public String getCreTime() {
		return creTime;
	}
	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}
	public Salary(int salary_id, int staff_id, String staff_name, int basicSalary, int bonus, int allSalary,
			String creTime) {
		super();
		this.salary_id = salary_id;
		this.staff_id = staff_id;
		this.staff_name = staff_name;
		this.basicSalary = basicSalary;
		this.bonus = bonus;
		this.allSalary = allSalary;
		this.creTime = creTime;
	}
	
}
	
	
