package com.arpan.productcatalog.repository;

import com.arpan.productcatalog.entity.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends RevisionRepository<ProductCategory, Integer, Integer>, JpaRepository<ProductCategory, Integer> {
}
