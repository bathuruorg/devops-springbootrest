package com.apple.simpleapprest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apple.simpleapprest.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
