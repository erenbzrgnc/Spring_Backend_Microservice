package com.sabanciuniv.model;

import java.util.Date;
import java.util.List;

public class AccountSummary {
	
	private String id;
	private String owner;
	private Date createDate;
	private List<Transaction> transactionOut;
	private List<Transaction> transactionIn;
	private Integer balance;
	
	
	public AccountSummary() {}
	public AccountSummary(String id, String owner, List<Transaction> transactionOut, List<Transaction> transactionIn, Integer balance) {
		
		this.id= id;
		this.owner = owner;
		this.createDate = new Date();
		this.transactionIn = transactionIn;
		this.transactionOut = transactionOut;
		this.balance = balance;
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
	public List<Transaction> getTransactionOut() {
		return transactionOut;
	}
	public void setTransactionOut(List<Transaction> transactionOut) {
		this.transactionOut = transactionOut;
	}
	public List<Transaction> getTransactionIn() {
		return transactionIn;
	}
	public void setTransactionIn(List<Transaction> transactionIn) {
		this.transactionIn = transactionIn;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	
}


