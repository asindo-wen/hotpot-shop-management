package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.enity.BillDetail;
import com.test.service.BillDetailService;

@RestController
@RequestMapping("/billdetail")
@CrossOrigin(allowCredentials = "true")
public class BillDetailController {
	
	@Autowired
	BillDetailService billDetailService;
	
	@RequestMapping("/insertBillDetail")
	@ResponseBody
	public boolean insertBillDetail(int bill_id,int dish_id,String dish_name,double dish_price,int dish_amount) {
		billDetailService.insertBillDetail(bill_id, dish_id, dish_name, dish_price, dish_amount);
		return true;
	}
	
	@RequestMapping("/deleteBillDetail")
	@ResponseBody
	public boolean deleteBills(int id) {
		billDetailService.deleteBillDetail(id);
		return true;
	}	
	
	@RequestMapping("/selectOne")
	@ResponseBody
	public List<BillDetail> selectOne(int bill_id) { 				
		return billDetailService.selectOne(bill_id);
	}
	
}
