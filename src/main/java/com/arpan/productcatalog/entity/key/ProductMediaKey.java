package com.arpan.productcatalog.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductMediaKey implements Serializable {
    @Column(name = "product_id")
    Long productId;
    @Column(name = "media_id")
    Long mediaId;
}
