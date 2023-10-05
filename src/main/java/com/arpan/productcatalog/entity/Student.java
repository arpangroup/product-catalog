package com.arpan.productcatalog.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tbl_student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;
}
