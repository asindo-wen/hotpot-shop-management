package com.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.enity.Bills;

@Service
public interface BillsService {
	//新增订单
	void insertBill(int desk_id,long bill_number,double bill_price,int  bill_status,String bill_date);
	//删除订单
	void deleteBill(int bill_id);
	//更改订单状态
	void updateStatus(int bill_status, int bill_id);
	//查询所有订单
	List<Bills> selectAll();
	//根据订单号查询
	Bills selectByNumber(long bill_number);		
	
	//根据bill_id查询订单
	Bills selectById(int bill_id);
	
	//根据时间区间查询订单
	List<Bills> selectByTime(String starTime,String endTime);
}
