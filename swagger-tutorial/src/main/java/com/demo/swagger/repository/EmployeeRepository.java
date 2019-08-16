package com.demo.swagger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.swagger.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
