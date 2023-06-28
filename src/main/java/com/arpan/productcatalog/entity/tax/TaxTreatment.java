package com.arpan.productcatalog.entity.tax;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@Table(
        name = "tbl_tax_treatment",
        uniqueConstraints = @UniqueConstraint(columnNames = {}),
        indexes = {
                @Index(name = "tax_treatment_name", columnList = "tax_treatment_name"),
                @Index(name = "product_code", columnList = "product_code")
//                @Index(name = "tax_policy_id", columnList = "tax_policy_id")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Audited
public class TaxTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "tax_treatment_name", length = 255, unique = false, nullable = true)
    private String name;

    @Column(name = "product_code", length = 80, unique = false, nullable = true)
    private String productCode;

    @Column(name = "tax_code", length = 80, unique = false, nullable = true)
    private String taxCode;

    @Column(name = "description", length = 1000)
    private String description;

    //Lookup(TaxPolicy)
    //@Indexed
//    private TaxPolicy taxPolicy;

    @Column(name = "is_active")
    private boolean isActive;
}
