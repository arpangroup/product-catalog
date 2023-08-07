package com.arpan.productcatalog.entity.product;

import com.arpan.productcatalog.entity.Auditable;
import com.arpan.productcatalog.entity.CategoryMedia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.sql.Date;
import java.util.Set;

@Entity(name = "tbl_product_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Audited
public class ProductCategory extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, unique = true, nullable = false, insertable = true, updatable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id", referencedColumnName = "id")
    private ProductCategory parentCategory;

    @Column(name = "description", length = 255, nullable = true, insertable = true, updatable = true, unique = false)
    private String description;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "sort_order", length = 1)
    private int sortOrder;

    private Date activeFrom;
    private Date activeTo;

    @OneToMany(mappedBy = "category")
    @NotAudited
    Set<CategoryMedia> medias;

    public ProductCategory(String name) {
        this.name = name;
    }
}
