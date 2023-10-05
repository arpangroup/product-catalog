package com.arpan.productcatalog.repository;

import com.arpan.productcatalog.entity.WebStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreRepository extends RevisionRepository<WebStore, Long, Long>, JpaRepository<WebStore, Long> {
    List<WebStore> findByCreatedBy(String createdBy);
    boolean existsByNameIgnoreCase(String storeName);
    Optional<WebStore> findByNameIgnoreCase(String name);
    Optional<WebStore> findByExternalReferenceId(UUID externalReferenceId);
    Integer countDistinctNameByNameIgnoreCase(String storeName);
    Integer countDistinctNameByNameStartsWithIgnoreCase(String storeName);
    List<WebStore> findByCatalogs_id(Long catalogId);

}
