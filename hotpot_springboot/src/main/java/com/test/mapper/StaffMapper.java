package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.enity.Staff;

@Mapper
public interface StaffMapper {
	
	// 添加员工
	@Insert("insert into staff value(null,#{staff_name},#{age},#{sex},#{phone},#{regTime})")
	void insertStaff(@Param("staff_name")String staff_name,  @Param("age")int age, @Param("sex")String sex,
			@Param("phone")long phone,@Param("regTime")String regTime);
	
	// 管理员删除员工
	@Delete("DELETE FROM `staff` WHERE staff_id=#{staff_id}")
	void deleteStaff(@Param("staff_id") int staff_id);
	
	// 修改员工信息
	@Update("update `staff` SET `staff_name`=#{staff_name},age=#{age},sex=#{sex},phone=#{phone} WHERE staff_id=#{staff_id}")
	void updateStaff(@Param("staff_name") String staff_name, @Param("age") int age, @Param("sex") String sex, 
			@Param("phone")long phone, @Param("staff_id") int staff_id);
	

	//查询所有管理员与员工
	@Select("SELECT * FROM `staff`")
	List<Staff> selectAll();
	
	//根据id查询员工
	@Select("SELECT * FROM `staff` WHERE staff_id=#{staff_id}")
	Staff selectOneById(@Param("staff_id") int staff_id);
	
	//根据员工名查询员工
	@Select("SELECT * FROM `staff` WHERE staff_name=#{staff_name}")
	Staff selectOneByName(@Param("staff_name") String staff_name);
	
	//根据员工名查询员工
	@Select("SELECT * FROM `staff` WHERE staff_name like '%${search_result}%'")
	List<Staff> selectResultLike(@Param("search_result") String search_result);
}
