package com.apple.simpleapprest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apple.simpleapprest.model.Employee;
import com.apple.simpleapprest.repository.EmployeeRepository;

@SpringBootApplication
public class SimpleappRestApplication implements CommandLineRunner{

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String...args) throws Exception {

        Employee employee = new Employee();
        employee.setFirstName("Srini");
        employee.setLastName("Bathuru");
        employee.setEmailId("srini@bathuru.com");
        employeeRepository.save(employee);
        // Create an employee
        Employee employee1 = new Employee();
        employee1.setFirstName("Sri");
        employee1.setLastName("Bathuru");
        employee1.setEmailId("sri@bathuru.com");
        employeeRepository.save(employee1);
     // Create an employee
        Employee employee2 = new Employee();
        employee2.setFirstName("Srinivas");
        employee2.setLastName("Bathuru");
        employee2.setEmailId("srinivas.bathuru@gmail.com");
        employeeRepository.save(employee2);
        System.out.println("print : " + employeeRepository);
    }
    
	public static void main(String[] args) {
		SpringApplication.run(SimpleappRestApplication.class, args);
	}

}
