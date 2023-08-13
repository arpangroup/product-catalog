package com.arpan.productcatalog.entity.product;

import com.arpan.productcatalog.entity.Auditable;
import com.arpan.productcatalog.entity.Catalog;
import com.arpan.productcatalog.entity.CategoryMedia;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "tbl_category", indexes = {
        @Index(name = "uniqueCatalogIdCategoryName", columnList = "catalog_id, name", unique = true)
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
@Builder
public class Category extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productCategorySeq")
    //@SequenceGenerator(name = "productCategorySeq", sequenceName = "product_category_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, unique = false, nullable = false, insertable = true, updatable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id", referencedColumnName = "id")
    private Category parentCategory;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "sort_order", length = 1)
    private Integer sortOrder;

    private Date activeFrom;
    private Date activeTo;

    @OneToMany(mappedBy = "category")
    @NotAudited
    Set<CategoryMedia> medias;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "catalog_id", nullable = false)
    Catalog catalog;

    public Category(String name) {
        this.name = name;
    }
}
