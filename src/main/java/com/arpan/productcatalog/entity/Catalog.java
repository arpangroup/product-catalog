package com.arpan.productcatalog.entity;

import com.arpan.productcatalog.entity.product.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tbl_catalog")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class Catalog extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogSeq")
    //@SequenceGenerator(name = "catalogSeq", sequenceName = "catalog_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, unique = true, nullable = false, insertable = true, updatable = true)
    private String name;


    @Column
    private Boolean isDefault = false;

    @Column
    private Boolean isActive = false;

    @Column(name = "owner_id", length = 10)
    private String ownerId;

    private String ownerName;

//    @ManyToMany(mappedBy = "catalogs")
//    List<Store> stores = new ArrayList<>();

    @OneToMany(mappedBy = "catalog")
    List<StoreCatalog> stores = new ArrayList<>();


    public Catalog assignStore(StoreCatalog storeCatalog) {
        this.stores.add(storeCatalog);
        return this;
    }



    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<Category> categories = new ArrayList<>();

    public Catalog(String catalogName) {
        this.name = catalogName;
    }

//    public Catalog attachStore(Store store) {
//        this.stores.add(store);
//        return this;
//    }

    public Catalog attachCategory(Category category) {
        this.categories.add(category);
        return this;
    }
//    @PrePersist
//    private void prePersist() {
//        categories.forEach(category -> category.setCatalog(this));
//        stores.forEach(store -> store.attachCatalog(this));
//    }
}

