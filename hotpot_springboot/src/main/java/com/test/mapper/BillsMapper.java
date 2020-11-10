package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.enity.Bills;

@Mapper
public interface BillsMapper {
//	#{},
			// 添加订单
			@Insert("insert into bills VALUES(null,#{desk_id},#{bill_number},#{bill_price},#{bill_status},#{bill_date})")
			void insertBill(@Param("desk_id")int desk_id,@Param("bill_number")long bill_number,@Param("bill_price")double bill_price,
					@Param("bill_status")int bill_status,@Param("bill_date")String bill_date);
			
			// 删除订单
			@Delete("DELETE FROM `bills` WHERE bill_id=#{bill_id}")
			void deleteBill(@Param("bill_id") int bill_id);
			
			//更改订单状态
			@Update("update `bills` SET bill_status=#{bill_status} where bill_id = #{bill_id}")
			void updateStatus(@Param("bill_status")int bill_status,@Param("bill_id") int bill_id);
			
			
			//查询所有订单
			@Select("SELECT * FROM bills")
			List<Bills> selectAll();
			
			//根据订单号查询订单
			@Select("SELECT * FROM bills WHERE bill_number=#{bill_number}")
			Bills selectByNumber(@Param("bill_number") long bill_number);
			
			//根据bill_id查询订单
			@Select("SELECT * FROM bills WHERE bill_id=#{bill_id}")
			Bills selectById(@Param("bill_id") int bill_id);
			
			//根据时间区间查询订单
			@Select("SELECT * FROM `bills` WHERE bill_date BETWEEN  '${starTime}' AND  '${endTime}'")
			List<Bills> selectByTime(@Param("starTime")String starTime,@Param("endTime")String endTime);

}
