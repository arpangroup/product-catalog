package com.arpan.productcatalog.repository;

import com.arpan.productcatalog.entity.StoreCatalog;
import com.arpan.productcatalog.entity.WebStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StoreCatalogRepository extends RevisionRepository<StoreCatalog, Long, Long>, JpaRepository<StoreCatalog, Long> {
    List<StoreCatalog> findByWebStore_id(Long webStoreId);
    List<StoreCatalog> findByCatalog_id(Long catalogId);
    List<StoreCatalog> findByCatalog_name(String catalogName);

//    @Query("select c from Catalog c join Store s where s.id = :storeId")
//    List<Catalog> findByStoreId(String storeId);

}
