
package com.sabanciuniv.model;



import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Transaction {


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Account getFrom() {
		return from;
	}

	public void setFrom(Account from) {
		this.from = from;
	}

	public Account getTo() {
		return to;
	}

	public void setTo(Account to) {
		this.to = to;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Id
	private String id;
	@DBRef
	private Account from;
	@DBRef
	private Account to;

	private Date createDate;
	private Double amount;
	
	public Transaction() {}
	
	public Transaction(Account from, Account to, Double amnt) {
		
		Date date = new Date();
		this.from = from;
		this.to= to;
		this.amount = amnt;
		this.setCreateDate(date);
	
	}
	
}
