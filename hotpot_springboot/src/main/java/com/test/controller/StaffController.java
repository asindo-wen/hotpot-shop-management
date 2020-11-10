package com.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.enity.Staff;
import com.test.service.StaffService;

@RestController
@RequestMapping("/staff")
@CrossOrigin(allowCredentials = "true")
public class StaffController {
	
	@Autowired
	StaffService staffservice;
	
	@RequestMapping("/insertStaff")
	@ResponseBody
	public boolean insertStaff(String staff_name, int age,long phone,String sex) {	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String regTime = sdf.format(new Date());		
		staffservice.insertStaff(staff_name, age, sex, phone, regTime);				
		return true;
	}
	
	@RequestMapping("/updateStaff")
	@ResponseBody
	public boolean updateStaff(String staff_name,int age,String sex,long phone,int staff_id) {
		staffservice.updateStaff(staff_name, age, sex, phone,staff_id);
		return true;		
	}
	
	@RequestMapping("/deleteStaff")
	@ResponseBody
	public boolean deleteStaff(int staff_id) {
		staffservice.deleteStaff(staff_id);
		return true;
	}
	
	@RequestMapping("/selectAllStaff")
	@ResponseBody
	public List<Staff> selectAll() {
		return staffservice.selectAll();
	}
	
	@RequestMapping("/selectOneById")
	@ResponseBody
	public Staff selectOneById(Integer staff_id) {
		return staffservice.selectOneById(staff_id);
	}

	@RequestMapping("/selectOneByName")
	@ResponseBody
	public Staff selectOneByName(String staff_name) {
		return staffservice.selectOneByName(staff_name);
	}
	
	@RequestMapping("/selectResultLike")
	@ResponseBody
	public List<Staff> selectResultLike(String search_result) {
		String result = search_result.replaceAll("\\s*","");//去除空白字符串
		List<Staff> staff = staffservice.selectResultLike(result);
		if(staff!=null) {
			return staff;
		}else {
			return null;
		}

	}
	

}
