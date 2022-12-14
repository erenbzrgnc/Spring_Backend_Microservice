package com.sabanciuniv.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sabanciuniv.model.Account;

public interface AccountRepo extends MongoRepository<Account, String> {
	
	@Query("{id:'?0'}")
	public List<Account> findWithParamId(String id);
	
	public Optional<Account> findById(String id);
	


}