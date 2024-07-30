package com.micro.service.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.micro.service.model.Company;
import com.micro.service.model.Store;
import com.micro.service.repository.CompanyRepository;
import com.micro.service.repository.StoreRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pbm/store")
@Validated
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerStore(@RequestBody @Valid Store store) {
        UUID companyId = store.getCompanyId();
        

        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            int storeCount = company.getStoreCount();
            List<Store> storeList = storeRepository.findAllByCompanyId(companyId);
            int storeSize = storeList.size();
            if (storeCount <= storeSize) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(createErrorResponse("PBM-400", "Store count is full for the given company Id"));
            }

            // Check if store name already exists
            Optional<Store> storeOptional = storeRepository.findByStoreName(store.getStoreName());
            if (storeOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(createErrorResponse("PBM-400", "Store Name already exists"));
            }

            // Check if store email already exists
            Optional<Store> storeByEmailOptional = storeRepository.findByStoreEmail(store.getStoreEmail());
            if (storeByEmailOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(createErrorResponse("PBM-400", "Store Email already exists"));
            }

            // Validate storeName and storeEmail
            validateStore(store);

            // Set createdDate with current date and time in yyyyMMddHHmmss format
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            store.setCreatedDate(dateTime.format(formatter));

            storeRepository.save(store);
            return ResponseEntity.ok().body(createSuccessResponse("PBM-200", "Store registered successfully", store));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(createErrorResponse("PBM-400", "Invalid Company ID"));
        }
    }

    private void validateStore(Store store) {
        if (store.getStoreName() == null || store.getStoreName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Store name is required");
        }

        if (store.getStoreEmail() == null || store.getStoreEmail().isEmpty() || !isValidEmail(store.getStoreEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Store email should be valid");
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    
    private Map<String, Object> createSuccessResponse(String code, String description, Store store) {
        Map<String, Object> successResponse = new LinkedHashMap<>();
        Map<String, String> message = new LinkedHashMap<>();
        message.put("code", code);
        message.put("description", description);
        successResponse.put("message", message);
        successResponse.put("data", store);
        return successResponse;
    }


 

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllStores() {
        List<Store> stores = storeRepository.findAll();

        Map<String, Object> response = new LinkedHashMap<>();
        Map<String, String> message = new LinkedHashMap<>();
        message.put("code", "PBM-200");
        message.put("description", "Success: Store details retrieved successfully");
        response.put("message", message);
        response.put("data", stores);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/byStoreId/{id}")
    public ResponseEntity<?> getStoreById(@PathVariable UUID id) {
        Optional<Store> store = storeRepository.findById(id);

        if (store.isPresent()) {
            Map<String, Object> response = new LinkedHashMap<>();
            Map<String, String> message = new LinkedHashMap<>();
            message.put("code", "PBM-200");
            message.put("description", "Success: Store details retrieved successfully");
            response.put("message", message);
            response.put("data", store.get());
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(404).body(createErrorResponse("PBM-404", "Given ID could not be found"));
        }
    }

    @PutMapping("/updateByStoreId/{id}")
    public ResponseEntity<?> updateStoreById(@PathVariable UUID id, 
    		                                 @RequestBody @Valid Store store) {
        Optional<Store> existingStoreOptional = storeRepository.findById(id);

        if (existingStoreOptional.isPresent()) {
            Store existingStore = existingStoreOptional.get();
            existingStore.setCompanyId(store.getCompanyId());
            existingStore.setStoreName(store.getStoreName());
            existingStore.setAddress(store.getAddress());
            existingStore.setStorePhone(store.getStorePhone());
            existingStore.setStoreAlternatePhone(store.getStoreAlternatePhone());
            existingStore.setStoreEmail(store.getStoreEmail());
            existingStore.setStoreAlternateEmail(store.getStoreAlternateEmail());
            existingStore.setCreatedBy(store.getCreatedBy());
            existingStore.setModifyBy(store.getModifyBy());

            // Update modifyDate with current date and time in yyyyMMddHHmmss format
            LocalDateTime dateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            existingStore.setModifyDate(dateTime.format(formatter));

            storeRepository.save(existingStore);

            Map<String, Object> response = new LinkedHashMap<>();
            Map<String, String> message = new LinkedHashMap<>();
            message.put("code", "PBM-200");
            message.put("description", "Store Updated Successfully");
            response.put("message", message);
            response.put("data", existingStore);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createErrorResponse("PBM-404", "Given Store ID could not be found"));
        }
    }

    @DeleteMapping("/deleteByStoreId/{id}")
    public ResponseEntity<?> deleteStoreById(@PathVariable UUID id) {
        Optional<Store> storeOptional = storeRepository.findById(id);

        if (storeOptional.isPresent()) {
            storeRepository.deleteById(id);

            Map<String, Object> response = new LinkedHashMap<>();
            Map<String, String> message = new LinkedHashMap<>();
            message.put("code", "PBM-200");
            message.put("description", "Store Deleted Successfully");
            response.put("message", message);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createErrorResponse("PBM-404", "Given Store ID could not be found"));
        }
    }
    


    private Map<String, String> createErrorResponse(String code, String description) {
        Map<String, String> errorResponse = new LinkedHashMap<>();
        errorResponse.put("code", code);
        errorResponse.put("description", description);
        return errorResponse;
    }
}