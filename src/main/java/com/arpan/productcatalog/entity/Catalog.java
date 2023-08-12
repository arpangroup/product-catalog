package com.arpan.productcatalog.entity;

import com.arpan.productcatalog.entity.product.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "tbl_catalog")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Audited
public class Catalog extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "catalog_name", length = 100, unique = true, nullable = false, insertable = true, updatable = true)
    private String catalogName;

    private boolean isDefault;
    private boolean isActive;

    @Column(name = "owner_id", length = 10)
    private String ownerId;

    private String ownerName;

    @ManyToMany(mappedBy = "catalogs")
    Set<Store> stores = new HashSet<>();

    @OneToMany(mappedBy = "catalog")
    Set<Category> categories = new HashSet<>();

    public Catalog(String catalogName) {
        this.catalogName = catalogName;
    }

    public Catalog attachStore(Store store) {
        this.stores.add(store);
        return this;
    }

    public Catalog attachCategory(Category category) {
        this.categories.add(category);
        return this;
    }
}
