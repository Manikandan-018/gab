package com.micro.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.micro.service.model.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {
  
}
