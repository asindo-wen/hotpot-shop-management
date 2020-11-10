package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.enity.Staff;
import com.test.mapper.StaffMapper;
import com.test.service.StaffService;

@Service
public class StaffServiceImpl implements StaffService{
	@Autowired
	StaffMapper staffmapper;
	
	@Override
	public void insertStaff(String staff_name, int age,String sex,long phone,String regTime){
		staffmapper.insertStaff(staff_name, age, sex, phone, regTime);		
	}
	

	@Override
	public void deleteStaff(int staff_id) {
		staffmapper.deleteStaff(staff_id);
	}
	
	@Override
	public void updateStaff(String staff_name,int age,String sex,long phone,int staff_id) {
		staffmapper.updateStaff(staff_name, age, sex,phone, staff_id);
	}
	
	@Override
	public List<Staff> selectAll(){

		return staffmapper.selectAll();
	}

	@Override
	public Staff selectOneById(int staff_id) {
		// TODO Auto-generated method stub
		return staffmapper.selectOneById(staff_id);
	}

	@Override
	public Staff selectOneByName(String staff_name) {
		// TODO Auto-generated method stub
		return staffmapper.selectOneByName(staff_name);
	}


	@Override
	public List<Staff> selectResultLike(String search_result) {
		// TODO Auto-generated method stub
		return staffmapper.selectResultLike(search_result);
	}
	
	
	
	
}
