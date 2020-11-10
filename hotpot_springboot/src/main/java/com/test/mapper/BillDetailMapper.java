package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.test.enity.BillDetail;

@Mapper
public interface BillDetailMapper {
			// 添加订单
			@Insert("insert into `billdetail` VALUES(null,#{bill_id},#{dish_id},#{dish_name},#{dish_price},#{dish_amount})")
			void insertBillDetail(@Param("bill_id")int bill_id,@Param("dish_id")int dish_id, @Param("dish_name")String dish_name,
			  					@Param("dish_price")double dish_price, @Param("dish_amount")int dish_amount);
			
			// 删除订单
			@Delete("DELETE FROM `billdetail` WHERE bill_id=#{bill_id}")
			void deleteBillDetail(@Param("bill_id") int bill_id);
			
			//查询当前订单
			@Select("SELECT * FROM billdetail where bill_id=#{bill_id}")
			List<BillDetail> selectOne(int bill_id);
			
			
}
