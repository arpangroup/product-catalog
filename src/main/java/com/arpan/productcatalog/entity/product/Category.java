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

@Entity(name = "tbl_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Audited
@Builder
public class Category extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 100, unique = true, nullable = false, insertable = true, updatable = false)
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

    @ManyToOne
    @JoinColumn(name = "catalog_id", nullable = false)
    Catalog catalog;

    public Category(String name) {
        this.name = name;
    }
}
