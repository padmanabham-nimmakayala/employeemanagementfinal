package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.Table;


import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


	@Entity
	@Table(name="project")
	public class Project  {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		@Column(name="projectName")
		@NonNull
		private String projectName;
		@NonNull
		@Column(name="start_date")
		private Date startdate;
		@NonNull
		@Column(name="end_date")
		private Date enddate;
	 
	    public List<Employee> getEmployees() {
			return employees;
		}
		public void setEmployees(List<Employee> employees) {
			this.employees = employees;
		}
		@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="projects")
		@JsonIgnore
		private List< Employee> employees;
		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		public Date getStartdate() {
			return startdate;
		}
		public void setStartdate(Date startdate) {
			this.startdate = startdate;
		}
		public Date getEnddate() {
			return enddate;
		}
		public void setEnddate(Date enddate) {
			this.enddate = enddate;
		}
public Project() {
	
}
}