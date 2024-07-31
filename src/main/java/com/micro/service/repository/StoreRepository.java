package com.micro.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.micro.service.model.Store;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends MongoRepository<Store, String> {

    List<Store> findAllByCompanyId(String companyId);

    Optional<Store> findByStoreName(String storeName);

    Optional<Store> findByStoreEmail(String storeEmail);
}
