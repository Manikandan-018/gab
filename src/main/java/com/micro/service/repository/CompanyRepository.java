package com.micro.service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.micro.service.model.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID> {


    Company findByCompanyName(String companyName);
}