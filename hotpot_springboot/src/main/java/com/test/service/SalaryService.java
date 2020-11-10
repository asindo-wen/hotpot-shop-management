package com.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.enity.Salary;

@Service
public interface SalaryService {
	
	void insertSalary(int staff_id,String staff_name,int basicSalary,int bonus,String creTime);
	
	void deleteSalary(int salary_id);
	
	void updateSalary(int staff_id,String staff_name,int basicSalary,int bonus, int salary_id);
	
	List<Salary> selectAll();
	
	Salary selectOneById(int salary_id);

}
