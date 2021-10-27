package com.pack.dao;

import java.util.List;

import com.pack.model.Employee;

public interface EmployeeDao {
	public void insertEmployee(Employee emp);
	
	public List<Employee> fetchAllEmployee();
	
	public void deleteEmployee(int empId);
	
	public Employee fetchEmployeeById(Integer empId);
	
	public void updateEmployee(Employee emp);
}