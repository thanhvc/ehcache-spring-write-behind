package net.danielwind.effcaching.recipe15.services;

import java.util.List;

import net.danielwind.effcaching.recipe15.domain.Employee;

public interface ApplicationService {
  
  /**
   * Simple method for retrieving the employee by Id
   * stored in the database.  
   * @return The employee
   */
  public Employee get(String employeeId);

	/**
	 * Simple service method for accessing Employees DAO
	 * @return Typed List of all employees available
	 */
	public List<Employee> findAllEmployees();
	
	/**
   * Simple method for inserting the 
   * given Employees to store database
   * 
   * @param emp The Employee
   */
  public void insert(Employee emp);
}
