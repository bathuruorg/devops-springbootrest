package com.apple.springbootrest.controller;

import java.util.List;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apple.springbootrest.exception.ResourceNotFoundException;
import com.apple.springbootrest.model.Employee;
import com.apple.springbootrest.service.EmployeeService;

@CrossOrigin (origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employeeList")
	public List<Employee> getAllEmployees() {
		logger.info(" Getting All employess details... ");
		return employeeService.getAllEmployees();
	}

	@GetMapping("/employeeList/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) 
	                                                 throws ResourceNotFoundException{
		logger.info(" Getting specific employess details... ");
		Employee employee = employeeService.getEmployeeById(employeeId);
		if (employee == null || employee.equals("")){
			throw new ResourceNotFoundException("User not found in DB");
		}
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/saveEmployee")
	public Employee createEmployee(@Valid @RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) {
		final Employee updatedEmployee = employeeService.updateEmployee(employeeId, employeeDetails);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		
		Employee employee = employeeService.getEmployeeById(employeeId);
		employeeService.deleteEmployee(employee);
	}
	
}
