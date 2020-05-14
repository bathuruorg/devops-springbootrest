package com.apple.springbootrest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apple.springbootrest.model.Employee;
import com.apple.springbootrest.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
        // Create an employee
		return this.employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(Long employeeId) {
		return this.employeeRepository.findById(employeeId);
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employeeDetails) {
		return this.employeeRepository.save(employeeDetails);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		this.employeeRepository.delete(employee);
	}

}
