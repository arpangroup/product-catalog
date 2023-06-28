package com.arpan.productcatalog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity(name = "tbl_media")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Media implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String name;


    @OneToMany(mappedBy = "media")
    Set<CategoryMedia> categories;
}
