package com.example.challenge6.repository;

import com.example.challenge6.model.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
