package com.arpan.productcatalog.entity.tax;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "tbl_tax_rate")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Audited
public class TaxRate{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "priority")
    private int priority;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "tax_rate_percentage")
    private float taxRatePercentage;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;
}
