package com.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.enity.BillDetail;

@Service
public interface BillDetailService {
			// 添加订单
			void insertBillDetail(int bill_id,int dish_id, String dish_name,double dish_price,int dish_amount);
			
			// 删除订单
			void deleteBillDetail( int id);
			
			//查询当前订单详情
			List<BillDetail> selectOne(int bill_id);
						
}
