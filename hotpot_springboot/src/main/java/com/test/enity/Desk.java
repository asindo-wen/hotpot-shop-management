package com.test.enity;

public class Desk {
	private int desk_id;
	private String desk_name;
	private int status;
	
	public int getDesk_id() {
		return desk_id;
	}
	public void setDesk_id(int desk_id) {
		this.desk_id = desk_id;
	}
	public String getDesk_name() {
		return desk_name;
	}
	public void setDesk_name(String desk_name) {
		this.desk_name = desk_name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Desk(int desk_id, String desk_name, int status) {
		super();
		this.desk_id = desk_id;
		this.desk_name = desk_name;
		this.status = status;
	}
	
	

}
