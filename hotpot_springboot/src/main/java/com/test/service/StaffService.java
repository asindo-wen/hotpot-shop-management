package com.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.enity.Staff;

@Service
public interface StaffService {
	
	void insertStaff(String staff_name, int age, String sex,long phone,String regTime);
	
	void deleteStaff( int staff_id);
	
	void updateStaff(String staff_name,int age,String sex,long phone,int staff_id);
	
	List<Staff> selectAll();
	
	Staff selectOneById(int staff_id);

	Staff selectOneByName(String staff_name);
	
	List<Staff> selectResultLike(String search_result);

	
}
