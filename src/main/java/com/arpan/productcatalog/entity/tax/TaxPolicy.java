package com.arpan.productcatalog.entity.tax;

import com.arpan.productcatalog.entity.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "tbl_tax_policy", indexes = {
        @Index(name = "tax_policy_name", columnList = "tax_policy_name")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Audited
public class TaxPolicy extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "tax_policy_name")
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    //lookup(TaxTreatment)
//    private DefaultTaxTreatment defaultTaxTreatment;


    @Column(name = "is_active")
    private boolean isActive;


}
