package com.apple.springbootrest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apple.springbootrest.model.Employee;
import com.apple.springbootrest.repository.EmployeeRepository;

@SpringBootApplication
public class SimpleappRestApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(SimpleappRestApplication.class, args);
	}

}
