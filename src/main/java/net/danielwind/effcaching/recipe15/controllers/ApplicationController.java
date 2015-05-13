package net.danielwind.effcaching.recipe15.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.danielwind.effcaching.recipe15.domain.Employee;
import net.danielwind.effcaching.recipe15.services.impl.ApplicationServiceImpl;
import net.danielwind.effcaching.recipe15.utils.JsonConverter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public final class ApplicationController {
	
	@Autowired
	private ApplicationServiceImpl applicationService;
	
	private static final Logger log = Logger.getLogger(ApplicationController.class);
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index(Model model) {
		return "index";
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/list/employees")
	@ResponseStatus(HttpStatus.OK)
	public void invokeEmployeeList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		log.info("--- Invoking controller method: invokeEmployeeList() ---");
		
		List<Employee> employees = applicationService.findAllEmployees();
		
		log.debug("--- The number of employees found is: " + employees.size());
		
		String json = JsonConverter.toJson(employees);
		PrintWriter out = response.getWriter();
		out.println((employees != null) ? json : "{}");
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/insert")
  @ResponseStatus(HttpStatus.OK)
  public void invokeInsertEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
    log.info("--- Invoking controller method: invokeInsertEmployee() ---");
    Employee emp = new Employee();
    
    emp.setFirstName("Thanh");
    emp.setLastName("Vu" + System.currentTimeMillis());
    emp.setDepartment("IT");
    emp.setSalary(50000);
    emp.setRole("staff");
    applicationService.insert(emp);
  }
	
	@RequestMapping(method = RequestMethod.POST, value="/employee/inject")
  @ResponseStatus(HttpStatus.OK)
  public void invokeBatchInsertEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
    log.info("--- Invoking controller method: invokeBatchInsertEmployee() ---");
    
    long number = Long.parseLong(request.getParameter("number"));
    Employee emp;
    for(int i = 0; i< number; i++) {
      emp = new Employee();
      
      emp.setFirstName("Thanh " + i);
      emp.setLastName("Vu" + System.currentTimeMillis());
      emp.setDepartment("IT");
      emp.setSalary(50000);
      emp.setRole("staff");
      applicationService.insert(emp);
    }
    
    log.info("--- Done to inject:" + number + " employees ---");
     
  }
	
	@RequestMapping(method = RequestMethod.POST, value="/employee")
  @ResponseStatus(HttpStatus.OK)
  public void invokeGetEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
    log.info("--- Invoking controller method: invokeGetEmployee() ---");
    String employeeId = (String)request.getParameter("employeeId");
    log.info("--- EmployeeId = " + employeeId + " ---");
    Employee emp = applicationService.get(employeeId);

    String json = JsonConverter.toJson(emp);
    PrintWriter out = response.getWriter();
    out.println((emp != null) ? json : "{}");
  }

}
