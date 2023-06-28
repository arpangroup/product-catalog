package com.arpan.productcatalog.entity.product;

import com.arpan.productcatalog.entity.Media;
import com.arpan.productcatalog.entity.key.ProductMediaKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "tbl_product_media")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductMedia implements Serializable {
    @EmbeddedId
    ProductMediaKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("mediaId")
    @JoinColumn(name = "media_id")
    private Media media;

    private String mediaType;
}
