package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Designation;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.repository.DesignationRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ProjectRepository;

@Service
public class EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DesignationRepository designationRepository;
	@Autowired
	private ProjectRepository projectRepository;

	public void createEmployee(@Valid Employee emp) {
		Designation dsg = new Designation();
		dsg = designationRepository.findById(emp.getDesignation().getId()).orElse(null);
		if (dsg == null) {
			dsg = new Designation();
			dsg.setDesignationName(emp.getDesignation().getDesignationName());
			designationRepository.save(dsg);
		}

		emp.setDesignation(dsg);
		employeeRepository.save(emp);
	}

	public void updateEmployeeDetails(Long emp_id, @Valid Employee emp) {
		Optional<Employee> empDetails = employeeRepository.findById(emp_id);
		if (empDetails.isPresent()) {
			Employee dbEmployee = empDetails.get();
			dbEmployee.setEmail(emp.getEmail());
			dbEmployee.setFirstName(emp.getFirstName());
			dbEmployee.setLastName(emp.getLastName());
			dbEmployee.setDateOfBirth(emp.getDateOfBirth());
			dbEmployee.setReportingManager(emp.getReportingManager());
			dbEmployee.setMobileNo(emp.getMobileNo());
			dbEmployee.setModifiedDate(new Date());
			dbEmployee.setDesignation(emp.getDesignation());
			dbEmployee.setProjects(emp.getProjects());
			employeeRepository.save(dbEmployee);
		} else {
			System.out.println("Employee record is not exists with this id" + emp_id);
		}

	}

	public List<Employee> fetchAllEmployees() {
		logger.info("Entered  Depo **************");
		List<Employee> ls = employeeRepository.findAll();
		return ls;

	}

	public void deleteEmployeeDetails(Long employeeId) {
		Optional<Employee> empDetails = employeeRepository.findById(employeeId);
		if (empDetails.isPresent()) {
			employeeRepository.delete(empDetails.get());
		} else {
			System.out.println("record does not exist with this id" + employeeId);

		}

	}

	public List<Employee> fetchEmployeesByIsActiveState() {
		List<Employee> ls = employeeRepository.findAll().stream().filter(emp -> emp.getIsActive())
				.collect(Collectors.toList());
		/*
		 * List<Employee> activeEmployees = new ArrayList<Employee>(); for (Employee emp
		 * : ls) { if (emp.getIsActive()) { activeEmployees.add(emp); } }
		 */

		return ls;
	}

	public List<Employee> fetchEmployeesById(Long id) {
		Optional<Project> ep = projectRepository.findById(id);
		return ep.get().getEmployees();

	}

}
