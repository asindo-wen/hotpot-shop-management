package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.enity.BillDetail;
import com.test.mapper.BillDetailMapper;
import com.test.service.BillDetailService;

@Service
public class BillDetailServiceImpl implements BillDetailService{
	
	@Autowired
	BillDetailMapper billDetailMapper;

	@Override
	public void insertBillDetail(int bill_id, int dish_id, String dish_name, double dish_price, int dish_amount) {
		// TODO Auto-generated method stub
		billDetailMapper.insertBillDetail(bill_id, dish_id, dish_name, dish_price, dish_amount);
	}

	@Override
	public void deleteBillDetail(int id) {
		// TODO Auto-generated method stub
		billDetailMapper.deleteBillDetail(id);
	}

	@Override
	public List<BillDetail> selectOne(int bill_id) {
		// TODO Auto-generated method stub
		return billDetailMapper.selectOne(bill_id);
	}
	
	
	
	
}
