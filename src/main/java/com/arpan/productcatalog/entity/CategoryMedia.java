package com.arpan.productcatalog.entity;

import com.arpan.productcatalog.entity.key.CategoryMediaKey;
import com.arpan.productcatalog.entity.product.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name = "tbl_category_media")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CategoryMedia implements Serializable {
    @EmbeddedId
    CategoryMediaKey id;

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    @ManyToOne
    @MapsId("mediaId")
    @JoinColumn(name = "media_id")
    private Media media;

    private String mediaType;
}
