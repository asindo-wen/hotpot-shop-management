package com.test.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.test.enity.Dish;

@Mapper
public interface DishMapper {
	
	// 添加临时菜
	@Insert("insert into dish value(null,#{dish_name},#{dish_price},#{dish_type})")
	void insertDish(@Param("dish_name")String dish_name,  @Param("dish_price")BigDecimal dish_price, @Param("dish_type")int dish_type);
	
	//删除菜品
	@Delete("DELETE FROM `dish` WHERE dish_id =#{dish_id}")
	void deleteDish(@Param("dish_id") int dish_id);
	
	// 更改菜品信息
	@Update("update `dish` SET `dish_name`=#{dish_name},dish_price=#{dish_price},dish_type=#{dish_type} WHERE dish_id=#{dish_id}")
	void updateDish(@Param("dish_name") String dish_name, @Param("dish_price") BigDecimal dish_price, @Param("dish_type") int dish_type, 
			 @Param("dish_id") int dish_id);
	
	//获取菜品
	@Select("SELECT * FROM dish ORDER BY dish_type")
	List<Dish> selectAll();	
	
	//获取不同类型菜品
	@Select("SELECT * FROM `dish` \r\n" + 
			"INNER JOIN `dish_type`\r\n" + 
			"WHERE dish.dish_type = dish_type.type_id AND type_id = #{type_id}")
	List<Dish> selectByType(int type_id);	
	
}
