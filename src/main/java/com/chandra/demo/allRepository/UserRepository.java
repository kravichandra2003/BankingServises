package com.chandra.demo.allRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chandra.demo.allEntities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserNameIgnoreCase(String userName);
}