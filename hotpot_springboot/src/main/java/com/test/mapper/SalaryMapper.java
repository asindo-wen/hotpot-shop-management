package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.enity.Salary;

@Mapper
public interface SalaryMapper {
		// 添加薪水
		@Insert("insert into `salary` VALUES "
				+ "(null,#{staff_id},#{staff_name},#{basicSalary},"
				+ "#{bonus},#{allSalary},#{creTime})")
		void insertSalary(@Param("staff_id")int staff_id,@Param("staff_name")String staff_name,
				@Param("basicSalary")int basicSalary,@Param("bonus")int bonus,
				@Param("allSalary")int allSalary,@Param("creTime")String creTime);
		
		// 删除薪水
		@Delete("DELETE FROM `salary` WHERE salary_id=#{salary_id}")
		void deleteSalary(@Param("salary_id") int salary_id);
		
		// 更新薪水
		@Update("update `salary` SET staff_id=#{staff_id}, staff_name=#{staff_name},"
				+ "basicSalary=#{basicSalary},bonus=#{bonus},allSalary=#{allSalary} "
				+ " WHERE salary_id=#{salary_id}")
		void updateSalary(@Param("staff_id")int staff_id,@Param("staff_name")String staff_name,
				@Param("basicSalary")int basicSalary,@Param("bonus")int bonus,
				@Param("allSalary")int allSalary,@Param("salary_id")int salary_id);

		//查询所有薪资
		@Select("SELECT * FROM salary")
		List<Salary> selectAll();
		
		//根据id查询用户
		@Select("SELECT * FROM `salary` WHERE salary_id=#{salary_id}")
		Salary selectOneById(@Param("salary_id") int salary_id);
		

}
