package com.test.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.enity.Dish;
import com.test.service.DishService;

@RestController
@RequestMapping("/dish")
@CrossOrigin(allowCredentials = "true")
public class DishController {
	@Autowired
	DishService dishService;
		
	@RequestMapping("/insertDish")
	@ResponseBody
	public boolean insertDish(String staff_name, BigDecimal dish_price, int dish_type) {
	
        dishService.insertDish(staff_name, dish_price, dish_type);	
		return true;
	}
	
	
	@RequestMapping("/updateDish")
	@ResponseBody
	public boolean updateDish(String dish_name,BigDecimal dish_price,int dish_type,int dish_id) {
		dishService.updateDish(dish_name, dish_price, dish_type, dish_id);;
		return true;		
	}
	
	@RequestMapping("/deleteDish")
	@ResponseBody
	public boolean deleteDish(int dish_id) {
		dishService.deleteDish(dish_id);
		return true;
	}
	
	@RequestMapping("/selectAll")
	@ResponseBody
	public List<Dish> selectAll() {
		return dishService.selectAll();
	}
	
	@RequestMapping("/selectByType")
	@ResponseBody
	public List<Dish> selectByType(int type_id) {
		return dishService.selectByType(type_id);
	}
	

}
