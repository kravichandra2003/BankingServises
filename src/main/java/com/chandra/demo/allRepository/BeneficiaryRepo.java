package com.chandra.demo.allRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chandra.demo.allEntities.Beneficiary;

@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary, Integer> {

	Beneficiary findByAccountNumber(Long parseLong);


}
