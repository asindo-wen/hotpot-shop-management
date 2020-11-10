package com.test.enity;

import java.math.BigDecimal;

public class Dish {
	private int dish_id;
	private String dish_name;
	private BigDecimal dish_price;
	private int dish_type;
	
	public int getDish_id() {
		return dish_id;
	}
	public void setDish_id(int dish_id) {
		this.dish_id = dish_id;
	}
	public String getDish_name() {
		return dish_name;
	}
	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}
	public BigDecimal getDish_price() {
		return dish_price;
	}
	public void setDish_price(BigDecimal dish_price) {
		this.dish_price = dish_price;
	}
	public int getDish_type() {
		return dish_type;
	}
	public void setDish_type(int dish_type) {
		this.dish_type = dish_type;
	}
	
	public Dish(int dish_id, String dish_name, BigDecimal dish_price, int dish_type) {
		super();
		this.dish_id = dish_id;
		this.dish_name = dish_name;
		this.dish_price = dish_price;
		this.dish_type = dish_type;
	}
	
}
