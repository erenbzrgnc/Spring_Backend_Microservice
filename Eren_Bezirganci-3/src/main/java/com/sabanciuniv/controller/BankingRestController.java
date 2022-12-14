package com.sabanciuniv.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.mapper.Mapper;
import org.apache.el.stream.Optional;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sabanciuniv.model.Account;
import com.sabanciuniv.model.AccountPayload;
import com.sabanciuniv.model.Transaction;
import com.sabanciuniv.model.TransactionPayload;
import com.sabanciuniv.repo.AccountRepo;
import com.sabanciuniv.repo.TransactionRepo;

import jakarta.annotation.PostConstruct;


@RestController
public class BankingRestController {
	
	@Autowired private AccountRepo accountRepo;
	@Autowired private TransactionRepo transactionRepo;

	private static final Logger logger = LoggerFactory.getLogger(BankingRestController.class);
	
	@PostConstruct
	public void init() {
		if(accountRepo.count() == 0 && transactionRepo.count() == 0) {
			Account acc1 = new Account("1111", "Jack Johns");
			Account acc2 = new Account("2222", "Henry Williams");
			
			accountRepo.save(acc1);
			accountRepo.save(acc2);
			
		
			
			double amt1 = 1500;
			double amt2 = 2500;
			Transaction t1 = new Transaction(acc1, acc2, amt1);
			Transaction t2 = new Transaction(acc2, acc1, amt2);
			
		
			
			
			transactionRepo.save(t1);
			transactionRepo.save(t2);
		
		}
		
		
	}
	
	
	@PostMapping("/account/save")
	public Map<String, Object> saveAccount(@RequestBody AccountPayload acc) {
		
		Map<String, Object> map = new LinkedHashMap<>();
		
		
		
		
		if(acc.getId() == null || acc.getOwner() == null || acc.getId() == "" || acc.getOwner() =="" ) {
		

			 map.put("message", "ERROR: missing fields");
			 map.put("data", null);
			 
			
			
		}else {
			
			Account accountToSave = new Account(acc.getId(), acc.getOwner());
			
			
			
			
			
			Account accountSaved = accountRepo.save(accountToSave);
		
			 map.put("message", "SUCCESS");
			 map.put("data", accountSaved);
		}
		
	
		
		 
		
		return map;
		
		
		
	}
	
	
	
	
	 @PostMapping("/transaction/save")
	 public Map<String,Object> saveTransaction(@RequestBody TransactionPayload t){
			Map<String, Object> map = new LinkedHashMap<>();
		

	
		
			String from = t.getFromAccountId();

			String to = t.getToAccountId();

			Double amount = t.getAmount();
		

			
			if( (from == "" || to == "" ) && t.getAmount() != null) {
				 map.put("message", "ERROR: account id");
				 map.put("data", null);
				
			}
			 List<Account> acc0 = accountRepo.findWithParamId(from);
			 List<Account> acc3 = accountRepo.findWithParamId(to);
			
			
			if(acc0.size() == 0 || acc3.size() == 0) {
				
				 map.put("message",  "ERROR: account id");
				 map.put("data", null);
			}else {
				
				java.util.Optional<Account> AccountL =	accountRepo.findById(to);
				logger.info(to);
				Account AccountTo = AccountL.get();
				
				java.util.Optional<Account> AccountL2 =	accountRepo.findById(from);
				logger.info(from);
				Account AccountFrom = AccountL2.get();
				
				Transaction newtr = new Transaction(AccountFrom, AccountTo, amount);
				Transaction transactionSaved =  transactionRepo.save(newtr);
				
				
				map.put("message", "SUCCESS");
				map.put("data", transactionSaved);
				
				
				
				
			}
			
			
			
			
		 
		 
			
			return map;
		 
		 
	 }
	 
	 @GetMapping("/account/{accountId}")
	 public Map<String,Object> AccountSummary(@PathVariable("accountId") String accountId){
		 Map<String, Object> map = new LinkedHashMap<>();
		 Map<String, Object> datas = new LinkedHashMap<>();
		 
		 List<Account> acc0 = accountRepo.findWithParamId(accountId);
		 
		 if(acc0.size() == 0) {
			 map.put("message", "ERROR:account doesn’t exist");
			 map.put("data", null);
			 
			 
		 }else {
			 
			 Account acc = accountRepo.findById(accountId).get();
			 String id = acc.getId();
			 logger.info(id);
			 
			 
			 
			 List<Transaction> outgoing = transactionRepo.findByFrom(acc);
			 List<Transaction> ingoing = transactionRepo.findByTo(acc);
			
			 Double balance;
			 Double outgoingAmount = 0.0;
			 Double ingoingAmount = 0.0;
		
			 
			 for(int i=0; i < outgoing.size(); i++) {
				 
				 outgoingAmount += outgoing.get(i).getAmount();

			 }
			 
			 for(int k=0; k < ingoing.size(); k++) {
				 
				 ingoingAmount += ingoing.get(k).getAmount();

			 }
			 
			 Double s1 = (double) ingoing.size();
			 Double s2 = (double) outgoing.size();
			 logger.info(s1.toString());
			 logger.info(s2.toString());
			 
			 logger.info(ingoingAmount.toString());
			 logger.info(outgoingAmount.toString());
			 balance = ingoingAmount -  outgoingAmount;
			 
			 
			 
			 
			 
			datas.put("id", acc.getId());
			datas.put("owner", acc.getOwner());
			datas.put("createDate", acc.getCreateDate());
			datas.put("transactionsOut", outgoing);
			datas.put("transactionsIn", ingoing);
			datas.put("balance", balance);
			
			map.put("message", "SUCCESS");
			map.put("data", datas);
			 
			 
			 
		 }
		 
		 
		
		 
		 
		 
		 
		 return map;
	 }
	 
	
	 @GetMapping("/transaction/to/{accountId}")
	 public Map<String,Object> IncomingTransaction(@PathVariable("accountId") String accountId){
		 List<Account> acc0 = accountRepo.findWithParamId(accountId);
		 Map<String, Object> map = new LinkedHashMap<>();
		 
		 if(acc0.size() == 0) {
			 map.put("message", "ERROR:account doesn’t exist");
			 map.put("data", null);
			 
		 }else {
			 Account acc = accountRepo.findById(accountId).get();
			 String id = acc.getId();
			 
			 List<Transaction> incoming = transactionRepo.findByTo(acc);
			 
			 map.put("message", "SUCCESS");
			 map.put("data", incoming);
			 
		 }
		 
		 
		 

		 
		 return map;
		 
		 
	 }
	@GetMapping("/transaction/from/{accountId}")
	 public Map<String,Object> OutgoingTransaction(@PathVariable("accountId") String accountId){
		
		List<Account> acc0 = accountRepo.findWithParamId(accountId);
	
		 Map<String, Object> map = new LinkedHashMap<>();
		 
		 if(acc0.size() == 0) {
			 map.put("message", "ERROR:account doesn’t exist");
			 map.put("data", null);
			 
		 }else {
			Account acc = accountRepo.findById(accountId).get();
			 String id = acc.getId();
			 
			 List<Transaction> outgoing = transactionRepo.findByFrom(acc);
			 
			 map.put("message", "SUCCESS");
			 map.put("data", outgoing);
			 
			 
			 
		 }
		 
		 
		 
		 

		 return map;
		 
		 
	 }
	
	
	
	
	

}
