package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@PostMapping("/employees")

	public ResponseEntity<String> createEmployee(@Valid @RequestBody Employee emp) {

		Employee employeeEmail = employeeRepository.findByEmail(emp.getEmail());
		logger.info("employeeEmail in createEmployee of EmployeeController is**************" + employeeEmail);
		if (null == employeeEmail) {
			employeeService.createEmployee(emp);
			return new ResponseEntity<String>("Emplyee created", HttpStatus.OK);
		} else {
			logger.info("Emplyee already exists with the email**************");
			return new ResponseEntity<String>("Emplyee already exists with the email", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {

		return employeeService.fetchAllEmployees();

	}

	@GetMapping("/employees/byactivestate")
	public List<Employee> getEmployeesByIsActiveState() {
		return employeeService.fetchEmployeesByIsActiveState();
	}
	   @GetMapping("/employees/byprojectId")
		public List<Employee>getEmployeesById(Long id){
			return employeeService.fetchEmployeesById(id);
		}
	
		
	
	@PutMapping("/employees/{id}")
	public void updateEmployee(@PathVariable(value = "emp_id") Long emp_id, @Valid @RequestBody Employee emp) {
		logger.info("employe email is.");
		employeeService.updateEmployeeDetails(emp_id, emp);
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable(value = "id") Long employeeId) {
		employeeService.deleteEmployeeDetails(employeeId);
	}
}
