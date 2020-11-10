package com.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.enity.Salary;
import com.test.service.SalaryService;

@RestController
@RequestMapping("/salary")
@CrossOrigin(allowCredentials = "true")
public class SalaryController {
	
	@Autowired
	SalaryService salayrservice;
	
	@RequestMapping("/insertsalary")
	@ResponseBody
	public boolean insertSalary(int staff_id,String staff_name,int basicSalary,int bonus) { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String creTime = sdf.format(new Date());
		
		salayrservice.insertSalary(staff_id,staff_name,basicSalary, bonus,creTime);
		
		return true;
	}
	
	@RequestMapping("/deletesalary")
	@ResponseBody
	public boolean deleteSalary(int salary_id) { 
		salayrservice.deleteSalary(salary_id);
		
		return true;
	}	
	
	@RequestMapping("/updatesalary")
	@ResponseBody
	public boolean updateSalary(int staff_id,String staff_name,int basicSalary,int bonus,int salary_id) { 
		salayrservice.updateSalary(staff_id,staff_name,basicSalary, bonus,salary_id);		
		return true;
	}
	
	
	@RequestMapping("/selectsalary")
	@ResponseBody
	public List<Salary> selectAll() { 				
		return salayrservice.selectAll();
	}
	
	@RequestMapping("/selectOneById")
	@ResponseBody
	public Salary selectOneById(Integer salary_id) {
		return salayrservice.selectOneById(salary_id);
	}

	
	
	
}
