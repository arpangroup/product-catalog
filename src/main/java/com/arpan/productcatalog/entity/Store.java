package com.arpan.productcatalog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "tbl_store")
@Table(indexes = {
        @Index(name = "uniqueIndex", columnList = "name", unique = true),
        @Index(name = "uniqueStoreExternalId", columnList = "name, external_reference_id", unique = true),
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Audited
public class Store extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, unique = true, nullable = false, insertable = true, updatable = false)
    private String name;

    @Column(name = "slug", length = 100, unique = true, nullable = false, insertable = true, updatable = false)
    private String slug;

    @Column(name = "external_reference_id", length = 100, unique = false, nullable = true)
    private UUID externalReferenceId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "store_catalog",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "catalog_id"))
    Set<Catalog> catalogs = new HashSet<>();

    public Store(@NonNull String storeName) {
        this.name = storeName;
    }

    public Store attachCatalog(Catalog catalog) {
        this.catalogs.add(catalog);
        return this;
    }
}
