package com.arpan.productcatalog.repository;

import com.arpan.productcatalog.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreRepository extends RevisionRepository<Store, Long, Long>, JpaRepository<Store, Long> {
    boolean existsByNameIgnoreCase(String storeName);
    Optional<Store> findByNameIgnoreCase(String name);
    Optional<Store> findByExternalReferenceId(UUID externalReferenceId);
    Integer countDistinctNameByNameIgnoreCase(String storeName);
    Integer countDistinctNameByNameStartsWithIgnoreCase(String storeName);

}
