package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.enity.Desk;
@Mapper
public interface DeskMapper {
	
	//查询所有桌台
	@Select("SELECT * FROM desk")
	List<Desk> selectAll();	
	
	
	//开台
	@Update("Update `desk` SET status=#{status} WHERE desk_id=#{desk_id}")
	void updateStatus(@Param("status") String status, @Param("desk_id") int desk_id);
	
	

}
