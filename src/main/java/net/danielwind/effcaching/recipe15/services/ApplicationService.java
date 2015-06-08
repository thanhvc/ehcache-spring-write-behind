package net.danielwind.effcaching.recipe15.services;

import java.util.List;

import net.danielwind.effcaching.recipe15.domain.EmployeeEntity;

public interface ApplicationService {
  
  /**
   * Simple method for retrieving the employee by Id
   * stored in the database.  
   * @return The employee
   */
  public EmployeeEntity get(String employeeId);

	/**
	 * Simple service method for accessing Employees DAO
	 * @return Typed List of all employees available
	 */
	public List<EmployeeEntity> findAllEmployees();
	
	/**
   * Simple method for inserting the 
   * given Employees to store database
   * 
   * @param emp The Employee
   */
  public void insert(EmployeeEntity emp);
}
