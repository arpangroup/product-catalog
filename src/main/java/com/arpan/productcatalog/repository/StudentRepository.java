package com.arpan.productcatalog.repository;

import com.arpan.productcatalog.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
