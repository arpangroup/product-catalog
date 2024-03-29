package com.arpan.productcatalog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.*;

@Entity(name = "tbl_web_store")
@Table(indexes = {
        @Index(name = "uniqueIndex", columnList = "name", unique = true),
        @Index(name = "uniqueStoreExternalId", columnList = "name, external_reference_id", unique = true),
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Audited
public class WebStore extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storeSeq")
    //@SequenceGenerator(name = "storeSeq", sequenceName = "store_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, unique = true, nullable = false, insertable = true, updatable = false)
    private String name;

    @Column(name = "slug", length = 100, unique = true, nullable = false, insertable = true, updatable = false)
    private String slug;

    @Column(name = "external_reference_id", length = 100, unique = false, nullable = true)
    private UUID externalReferenceId;

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_store_catalog",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "catalog_id"))
    List<Catalog> catalogs = new ArrayList<>();

    public Store attachCatalog(Catalog catalog) {
        this.catalogs.add(catalog);
        return this;
    }*/


    @OneToMany(mappedBy = "webStore")
    List<StoreCatalog> catalogs = new ArrayList<>();

    public WebStore assignCatalog(StoreCatalog storeCatalog) {
        this.catalogs.add(storeCatalog);
        return this;
    }


    public WebStore(@NonNull String storeName) {
        this.name = storeName;
    }



}
