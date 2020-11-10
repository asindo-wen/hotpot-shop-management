package com.test.enity;

public class BillDetail {
	private int id;
	private int bill_id;
	private int dish_id;
	private String dish_name;
	private double dish_price;
    private int dish_amount;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
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
	public double getDish_price() {
		return dish_price;
	}
	public void setDish_price(double dish_price) {
		this.dish_price = dish_price;
	}
	public int getDish_amount() {
		return dish_amount;
	}
	public void setDish_amount(int dish_amount) {
		this.dish_amount = dish_amount;
	}
	public BillDetail(int id, int bill_id, int dish_id, String dish_name, double dish_price, int dish_amount) {
		super();
		this.id = id;
		this.bill_id = bill_id;
		this.dish_id = dish_id;
		this.dish_name = dish_name;
		this.dish_price = dish_price;
		this.dish_amount = dish_amount;
	}
	public BillDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	

}
