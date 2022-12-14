package com.sabanciuniv.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Account {
	
	private String id;
	private String owner;
	private Date createDate;
	

	
	public Account() {}
	public Account(String id, String owner) {
		super();
		
		Date date = new Date();
		
       
  
		this.id = id;
		this.owner = owner;
		this.createDate = date;
		
		
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
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	
}

