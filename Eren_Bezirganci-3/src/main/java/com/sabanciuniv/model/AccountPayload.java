package com.sabanciuniv.model;


import java.util.Date;

public class AccountPayload {
	private String id;
	private String owner;
	private Date createDate;
	
	public AccountPayload() {}
	public AccountPayload(String id, String owner) {
		super();
		
		Date date = new Date();
       
        
		this.setId(id);
		this.setOwner(owner);
		this.setCreateDate(date);
		
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date strDate) {
		this.createDate = strDate;
	}
	
	

}
