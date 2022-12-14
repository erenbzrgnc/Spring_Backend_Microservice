package com.sabanciuniv.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sabanciuniv.model.Account;
import com.sabanciuniv.model.Transaction;

public interface TransactionRepo extends MongoRepository<Transaction, String> {
	
	public List<Transaction> findByAmountLessThan(Double amount);
	
	
	public List<Transaction> findByFrom(Account from);
	public List<Transaction> findByTo(Account to);
	

	
	


}