package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.enity.Bills;
import com.test.mapper.BillsMapper;
import com.test.service.BillsService;

@Service
public class BillServiceImpl implements BillsService{
	
	@Autowired
	BillsMapper billsMapper;

	@Override
	public void insertBill(int desk_id, long bill_number, double bill_price, int bill_status, String bill_date) {
		// TODO Auto-generated method stub
		billsMapper.insertBill(desk_id, bill_number, bill_price, bill_status, bill_date);
	}

	@Override
	public void deleteBill(int bill_id) {
		// TODO Auto-generated method stub
		billsMapper.deleteBill(bill_id);
	}

	@Override
	public void updateStatus(int bill_status, int bill_id) {
		// TODO Auto-generated method stub
		billsMapper.updateStatus(bill_status, bill_id);
	}

	@Override
	public List<Bills> selectAll() {
		// TODO Auto-generated method stub
		return billsMapper.selectAll();
	}

	@Override
	public Bills selectByNumber(long bill_number) {
		// TODO Auto-generated method stub
		return billsMapper.selectByNumber(bill_number);
	}

	@Override
	public Bills selectById(int bill_id) {
		// TODO Auto-generated method stub
		return billsMapper.selectById(bill_id);
	}

	@Override
	public List<Bills> selectByTime(String starTime, String endTime) {
		// TODO Auto-generated method stub
		return billsMapper.selectByTime(starTime, endTime);
	}
	
	
	
}
