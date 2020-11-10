package com.test.enity;


public class Bills {
	 private int bill_id;
	 private int desk_id;
	 private long bill_number;
	 private double bill_price;
	 private int bill_status;
	 private String bill_date;
	 
	 
	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
	public long getBill_number() {
		return bill_number;
	}
	public void setBill_number(long bill_number) {
		this.bill_number = bill_number;
	}
	public double getBill_price() {
		return bill_price;
	}
	public void setBill_price(double bill_price) {
		this.bill_price = bill_price;
	}
	
	public int getDesk_id() {
		return desk_id;
	}
	public void setDesk_id(int desk_id) {
		this.desk_id = desk_id;
	}
	public int getBill_status() {
		return bill_status;
	}
	public void setBill_status(int bill_status) {
		this.bill_status = bill_status;
	}
	public String getBill_date() {
		return bill_date;
	}
	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	public Bills(int bill_id, int desk_id, long bill_number, double bill_price, int bill_status, String bill_date) {
		super();
		this.bill_id = bill_id;
		this.desk_id = desk_id;
		this.bill_number = bill_number;
		this.bill_price = bill_price;
		this.bill_status = bill_status;
		this.bill_date = bill_date;
	}
	
	 
}
