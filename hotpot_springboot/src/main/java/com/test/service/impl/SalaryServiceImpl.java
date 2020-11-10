package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.enity.Salary;
import com.test.mapper.SalaryMapper;
import com.test.service.SalaryService;

@Service
public class SalaryServiceImpl implements SalaryService{
	@Autowired
	SalaryMapper salarymapper;
	
	@Override
	public void insertSalary(int staff_id,String staff_name,int basicSalary,int bonus,String creTime){
		salarymapper.insertSalary(staff_id,staff_name,basicSalary, bonus, basicSalary+bonus,creTime);		
	}
	
	@Override
	public void deleteSalary( int salary_id) {
		salarymapper.deleteSalary(salary_id);
	}
	
	@Override
	public void updateSalary(int staff_id,String staff_name,int basicSalary,int bonus,int salary_id){
		salarymapper.updateSalary(staff_id,staff_name,basicSalary, bonus, basicSalary+bonus,salary_id);
	}
	
	@Override
	public List<Salary> selectAll(){
		return salarymapper.selectAll();
	}

	@Override
	public Salary selectOneById(int salary_id) {
		// TODO Auto-generated method stub
		return salarymapper.selectOneById(salary_id);
	}
	
	

	
	
}
