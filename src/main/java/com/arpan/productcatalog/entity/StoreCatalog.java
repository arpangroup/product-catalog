package com.arpan.productcatalog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"webstore_id"})
        },
        indexes = {
                @Index(name = "indexWebStoreId", columnList = "webstore_id", unique = true),
                @Index(name = "indexCatalogId", columnList = "catalog_id", unique = false),
        }
)
@Entity(name = "tbl_store_catalog")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class StoreCatalog extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "webstore_id", unique = true)
    WebStore webStore;

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    Catalog catalog;

    LocalDateTime registeredAt;

}
