package com.test.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.enity.Dish;
import com.test.mapper.DishMapper;
import com.test.service.DishService;

@Service
public class DishServiceImpl implements DishService{
	@Autowired
	DishMapper dishmapper;

	@Override
	public void insertDish(String dish_name, BigDecimal dish_price, int dish_type) {
		// TODO Auto-generated method stub
		dishmapper.insertDish(dish_name, dish_price, dish_type);
	}

	@Override
	public void deleteDish(int dish_id) {
		// TODO Auto-generated method stub
		dishmapper.deleteDish(dish_id);
	}

	@Override
	public void updateDish(String dish_name, BigDecimal dish_price, int dish_type,int dish_id) {
		// TODO Auto-generated method stub
		dishmapper.updateDish(dish_name, dish_price, dish_type, dish_id);
	}

	@Override
	public List<Dish> selectAll() {
		// TODO Auto-generated method stub
		return dishmapper.selectAll();
	}

	@Override
	public List<Dish> selectByType(int type_id) {
		// TODO Auto-generated method stub
		return dishmapper.selectByType(type_id);
	}
	
	
	
}
