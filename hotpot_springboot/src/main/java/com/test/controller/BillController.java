package com.test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.enity.Bills;
import com.test.service.BillsService;

@RestController
@RequestMapping("/bills")
@CrossOrigin(allowCredentials = "true")
public class BillController {
	
	@Autowired
	BillsService billService;
	
	@RequestMapping("/insertBills")
	@ResponseBody
	public boolean insertBills(int desk_id,long bill_number, double bill_price,int bill_status) {
        //自动获取创建时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        String bill_date = sdf.format(new Date());
		
		billService.insertBill(desk_id,bill_number,bill_price,bill_status,bill_date);
		return true;
	}
	
	@RequestMapping("/deleteBills")
	@ResponseBody
	public boolean deleteBills(int bill_id) { 
		billService.deleteBill(bill_id);		
		return true;
	}	
	//更改订单状态
	@RequestMapping("/selectBill")
	@ResponseBody
	public boolean updateStatus(int bill_status,int bill_id) { 
		billService.updateStatus(bill_status, bill_id);	
		return true;
	}
	
	
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<Bills> selectAll() { 				
		return billService.selectAll();
	}
	//根据订单号查询订单
	@RequestMapping("/selectByNumber")
	@ResponseBody
	public Bills selectByNumber(long bill_number) { 				
		return billService.selectByNumber(bill_number);
	}
	
	
	
	@RequestMapping("/selectById")
	@ResponseBody
	public Bills selectById(int bill_id) { 				
		return billService.selectById(bill_id);
	}
	
	@RequestMapping("/selectByTime")
	@ResponseBody
	public List<Bills> selectByTime(String starTime,String endTime){ 				
		return billService.selectByTime(starTime, endTime);
	}
	
	//yyyy-MM-dd HH:mm:ss时间格式
	@RequestMapping("/selectByDay")
	@ResponseBody
	public List<Bills> selectByTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
        String Time = sdf.format(new Date());

		String starTime = Time.concat(" 00:00:00");
		String endTime = Time.concat(" 23:59:59");
		return billService.selectByTime(starTime, endTime);
	}
	
}
