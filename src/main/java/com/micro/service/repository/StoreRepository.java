package com.micro.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.micro.service.model.Store;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {

	List<Store> findAllByCompanyId(UUID companyId);

    Optional<Store> findByStoreName(String storeName);

    Optional<Store> findByStoreEmail(String storeEmail);
}

