package com.employeeMangementServices.controller;

import java.util.List;
import java.util.Optional;

import com.employeeMangementServices.response.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeMangementServices.exception.ResourceNotFoundException;
import com.employeeMangementServices.model.Employee;
import com.employeeMangementServices.repository.EmployeeRepository;

import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeManagementController {
	
	@Autowired
	private EmployeeRepository repo;
	
	@ApiOperation(value = "retrieves all the employees",
			notes="A list Of employees",
			response= Employee.class,
			produces="application/json"
			)
	
	//get all employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return repo.findAll();
	}
	
	//adding Employees
	@PostMapping("/employees")
	public Employee createEmployee(@Validated @RequestBody Employee employee) {
		return repo.save(employee);
	}
	
	//get by Id
	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmployee( @PathVariable int id) {
		Optional<Employee> employee = repo.findById(id);// select * from employee
		if(employee.isPresent()) {
			return  ResponseEntity.ok(employee.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//update Employee details
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employee){
		Employee employee1 = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : "+ id ) );
		employee1.setFirstName(employee.getFirstName());
		employee1.setLastName(employee.getLastName());
		employee1.setEmailId(employee.getEmailId());
		repo.save(employee1);
		return ResponseEntity.ok(employee1);

	}
	
	@DeleteMapping("/employees/{id}")
	public  ResponseEntity<String> deleteEmployee(@PathVariable int id){
		Employee employee1 = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id : "+ id ) );
		repo.delete(employee1);
		return ResponseEntity.ok("Record is Deleted Successfully");
	}
}
