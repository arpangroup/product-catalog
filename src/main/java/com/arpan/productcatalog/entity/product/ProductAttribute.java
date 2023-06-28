package com.arpan.productcatalog.entity.product;

import com.arpan.productcatalog.entity.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.Set;

@Entity(name = "tbl_product_attribute")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Audited
public class ProductAttribute extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "sequence")
    private int sequence;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "variant_parent_id", referencedColumnName = "id")
//    private Product variantParent;

}
