package com.apple.springbootrest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apple.springbootrest.model.Employee;

@Service
public interface EmployeeService {
	
	public List<Employee> getAllEmployees();

	public Optional<Employee> getEmployeeById(Long employeeId);

	public Employee createEmployee(Employee employee);

	public Employee updateEmployee(Employee employeeDetails);

	public void deleteEmployee(Employee employee);

}
