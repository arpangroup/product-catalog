package com.arpan.productcatalog.entity.product;

import com.arpan.productcatalog.entity.Auditable;
import com.arpan.productcatalog.entity.enums.ProductClass;
import com.arpan.productcatalog.entity.enums.ProductFamily;
import com.arpan.productcatalog.entity.enums.ProductType;
import com.arpan.productcatalog.entity.enums.QuantityUnitOfMeasure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "tbl_product",
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_code"}),
        indexes = {
                @Index(name = "PRODUCT_CODE_INDEX", columnList = "product_code", unique = true),
                @Index(name = "PRODUCT_NAME_INDEX", columnList = "name")
//                @Index(name = "seller_id", columnList = "seller_id")
//                @Index(name = "parent_product_id", columnList = "parent_product_id")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Audited
public class Product extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 255, unique = false, nullable = false, insertable = true, updatable = false)
    private String name;

    @Column(name = "product_code", length = 255, nullable = true)
    private String productCode; //StockKeepingUnit

    @Column(name = "sku", length = 180)
    private String sku; //StockKeepingUnit

    @Column(name = "description", length = 4000)
    private String description;

    @Column(name = "display_url", length = 1000)
    private String displayUrl;

    @Column(name = "external_id", length = 255)
    private String externalId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_product_id", referencedColumnName = "id")
    private Product parentProduct;

    @Column(name = "product_type", nullable = true)
    @Enumerated(value = EnumType.STRING)
    private ProductType productType = ProductType.PRODUCT;//picklist

    @Column(name = "product_class")
    @Enumerated(value = EnumType.STRING)
    private ProductClass productClass;//picklist

    @Column(name = "product_family")
    @Enumerated(value = EnumType.STRING)
    private ProductFamily productFamily;//picklist

    @Column(name = "quantity_unit_of_measure")
    @Enumerated(value = EnumType.STRING)
    private QuantityUnitOfMeasure quantityUnitOfMeasure;//picklist

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "seller_id", referencedColumnName = "id")
//    private Seller seller;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "tax_policy_id", referencedColumnName = "id")
//    private TaxPolicy taxPolicy;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToMany(mappedBy = "products")
    private List<Category> categoryList = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @NotAudited
    List<ProductMedia> medias = new ArrayList<>();

    public Product (String productName) {
        this.name = productName;
    }
    
    public Product addCategory(Category category) {
        categoryList.add(category);
        return this;
    }
}
