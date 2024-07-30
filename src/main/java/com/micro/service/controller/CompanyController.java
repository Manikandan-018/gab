package com.micro.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.micro.service.model.Company;
import com.micro.service.repository.CompanyRepository;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/pbm/company")
@Validated
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerCompany(@RequestBody Company company) {
        validateCompany(company);

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        company.setCreatedDate(dateTime.format(formatter));

        // Save the company, id will be auto-generated
        companyRepository.save(company);

        Map<String, Object> response = new LinkedHashMap<>();
        Map<String, String> message = new LinkedHashMap<>();
        message.put("code", "PBM-200");
        message.put("description", "Company Registered Successfully");
        response.put("message", message);
        response.put("data", company);
        return ResponseEntity.ok().body(response);
    }



    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        
        Map<String, Object> response = new LinkedHashMap<>();
        Map<String, String> message = new LinkedHashMap<>();
        message.put("code", "PBM-200");
        message.put("description", "Success: Company details retrieved successfully");
        response.put("message", message);
        response.put("data", companies);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/byCompanyId/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable UUID id) {
        Optional<Company> company = companyRepository.findById(id);

        if (company.isPresent()) {
            Map<String, Object> response = new LinkedHashMap<>();
            Map<String, String> message = new LinkedHashMap<>();
            message.put("code", "PBM-200");
            message.put("description", "Success: Company details retrieved successfully");
            response.put("message", message);
            response.put("data", company.get());
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(404).body(createErrorResponse("PBM-404", "Given ID could not be found"));
        }
    }
    

    @PutMapping("/updateByCompanyId/{id}")
    public ResponseEntity<?> updateCompanyById(@PathVariable UUID id,
                                               @Valid @RequestBody Company company) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isPresent()) {
            Company existingCompany = companyOptional.get();

            if (company.getCompanyName() != null) {
                existingCompany.setCompanyName(company.getCompanyName());
            }
            if (company.getCompanyPhone() != null) {
                existingCompany.setCompanyPhone(company.getCompanyPhone());
            }
            if (company.getCompanyAlternatePhone() != null) {
                existingCompany.setCompanyAlternatePhone(company.getCompanyAlternatePhone());
            }
            if (company.getCompanyEmail() != null) {
                existingCompany.setCompanyEmail(company.getCompanyEmail());
            }
            if (company.getCompanyAlternateEmail() != null) {
                existingCompany.setCompanyAlternateEmail(company.getCompanyAlternateEmail());
            }
            if (company.getStoreCount() != null) {
                existingCompany.setStoreCount(company.getStoreCount());
            }
            if (company.getModifyBy() != null) {
                existingCompany.setModifyBy(company.getModifyBy());
            }
            if (company.getModifyDate() != null) {
                existingCompany.setModifyDate(company.getModifyDate());
            } else {
                LocalDateTime dateTime = LocalDateTime.now();
                existingCompany.setModifyDate(dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            }

            // Update address fields if provided
            if (company.getAddress() != null) {
                Company.CompanyAddress newAddress = company.getAddress();
                if (newAddress.getCompanyStreet() != null) {
                    existingCompany.getAddress().setCompanyStreet(newAddress.getCompanyStreet());
                }
                if (newAddress.getCompanyArea() != null) {
                    existingCompany.getAddress().setCompanyArea(newAddress.getCompanyArea());
                }
                if (newAddress.getCompanyCity() != null) {
                    existingCompany.getAddress().setCompanyCity(newAddress.getCompanyCity());
                }
                if (newAddress.getCompanyPinCode() != null) {
                    existingCompany.getAddress().setCompanyPinCode(newAddress.getCompanyPinCode());
                }
            }

            companyRepository.save(existingCompany);

            Map<String, Object> response = new LinkedHashMap<>();
            Map<String, String> message = new LinkedHashMap<>();
            message.put("code", "PBM-200");
            message.put("description", "Company details updated successfully");
            response.put("message", message);
            response.put("data", existingCompany);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(404).body(createErrorResponse("PBM-404", "Given ID could not be found"));
        }
    }



    @DeleteMapping("/deleteByCompanyId/{id}")
    public ResponseEntity<?> deleteCompanyById(@PathVariable UUID id) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isPresent()) {
            companyRepository.deleteById(id);
            Map<String, Object> response = new LinkedHashMap<>();
            Map<String, String> message = new LinkedHashMap<>();
            message.put("code", "PBM-200");
            message.put("description", "Company deleted successfully");
            response.put("message", message);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(404).body(createErrorResponse("PBM-404", "Given ID could not be found"));
        }
    }

    private void validateCompany(Company company) {
        if (company.getCompanyName() == null || company.getCompanyName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Company name is required");
        }
        if (company.getCompanyEmail() == null || company.getCompanyEmail().isEmpty() ||
                !company.getCompanyEmail().contains("@") || !company.getCompanyEmail().contains(".")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valid email is required");
        }
    }
    

    private Map<String, String> createErrorResponse(String code, String description) {
        Map<String, String> errorResponse = new LinkedHashMap<>();
        errorResponse.put("code", code);
        errorResponse.put("description", description);
        return errorResponse;
    }
}
