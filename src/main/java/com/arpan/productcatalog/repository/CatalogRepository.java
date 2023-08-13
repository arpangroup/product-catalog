package com.arpan.productcatalog.repository;

import com.arpan.productcatalog.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends RevisionRepository<Catalog, Long, Long>, JpaRepository<Catalog, Long> {
}
