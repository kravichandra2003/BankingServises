package com.chandra.demo.allRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chandra.demo.allEntities.Accounts;

@Repository
public interface AccountsRepo extends JpaRepository<Accounts, Integer> {

	Accounts findByAccountNumber(Long l);


}
