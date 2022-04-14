package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
	public Employee findByEmail(String email);
	
	
	@Query(value="select * from Employee where id = ?1 ", nativeQuery = true)
	Employee findById(long id);
}
