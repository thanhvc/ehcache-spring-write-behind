package net.danielwind.effcaching.recipe15.dao;

import java.util.List;

import net.danielwind.effcaching.recipe15.domain.Employee;

import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public interface EmployeeDao {
	
	/**
	 * Simple method for retrieving all employees
	 * stored in the database.  
	 * @return Typed List of All Employees
	 */
	public List<Employee> findAll();
	
	/**
   * Simple method for retrieving the employee by Id
   * stored in the database.  
   * @return The employee
   */
  public Employee get(String employeeId);
	
	/**
	 * Simple method for inserting the 
	 * given Employees to store database
	 * 
	 * @param emp The Employee
	 */
	public Employee insert(Employee emp);
	
	/**
	 * Gets HsqlMaxValueIncrementer instance
	 * @return
	 */
	public DataFieldMaxValueIncrementer getIncrementer();
	
	
	
}
