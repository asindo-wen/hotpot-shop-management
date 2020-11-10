package com.test.service;

import java.math.BigDecimal;
import java.util.List;

import com.test.enity.Dish;

public interface DishService {
	
	void insertDish(String dish_name, BigDecimal dish_price, int dish_type);
	
	void deleteDish( int dish_id);
	
	void updateDish(String dish_name, BigDecimal dish_price, int dish_type,int dish_id);
	
	List<Dish> selectAll();
	
	List<Dish> selectByType(int type_id);


}
