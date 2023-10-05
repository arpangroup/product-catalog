package com.arpan.productcatalog.repository;

import com.arpan.productcatalog.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


//https://stackoverflow.com/questions/33438483/spring-data-jpa-query-manytomany
//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-property-expressions
@Repository
public interface CatalogRepository extends RevisionRepository<Catalog, Long, Long>, JpaRepository<Catalog, Long> {
    List<Catalog> findByStores_Id(Long storeId);
    List<Catalog>findByNameIgnoreCase(String name);
    long countByNameIgnoreCase(String name);

//    @Query("select c from Catalog c join Store s where s.id = :storeId")
//    List<Catalog> findByStoreId(String storeId);
}
