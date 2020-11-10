package com.test.dto;

public class StaffSalaryInfor {
	
	private String staff_name;
	private int basicSalary;
	private int bonus;
	private int allSalary;
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
	public StaffSalaryInfor(String staff_name, int basicSalary, int bonus) {
		super();
		this.staff_name = staff_name;
		this.basicSalary = basicSalary;
		this.bonus = bonus;
		this.allSalary = bonus+basicSalary;
	}
	
	
}
