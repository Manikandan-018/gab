package com.micro.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.micro.service.model.Company;

public interface CompanyRepository extends MongoRepository<Company, String> {

    Company findByCompanyName(String companyName);
}
