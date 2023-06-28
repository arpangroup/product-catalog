package com.arpan.productcatalog.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;


@Getter
@Setter
@MappedSuperclass // it indicate its a super class, not a JPA entity, hence no extra table
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

    @CreatedBy
    protected String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Timestamp createdDate;


    @LastModifiedBy
    protected String lastModifiedBy;


    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Timestamp lastModifiedDate;
}
