/*
 * Copyright (C) 2003-2015 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.danielwind.effcaching.recipe15.services.endpoints;

import net.danielwind.effcaching.recipe15.domain.EmployeeEntity;
import net.danielwind.effcaching.recipe15.services.impl.ApplicationServiceImpl;
import net.danielwind.effcaching.recipe15.ws.Employee;
import net.danielwind.effcaching.recipe15.ws.EmployeeDetailRequest;
import net.danielwind.effcaching.recipe15.ws.EmployeeDetailResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Jun 8, 2015  
 */
public class EmployeeServiceEndpoint {
  //http://localhost:8080/recipe15/endpoints/EmployeeDetailsService.wsdl
  private static final String TARGET_NAMESPACE = "http://localhost:8080/ws";
  
  @Autowired
  private ApplicationServiceImpl employeeService;
  
  @PayloadRoot(localPart = "EmployeeDetailRequest", namespace = TARGET_NAMESPACE)
  public @ResponsePayload EmployeeDetailResponse getEmployeeDetail(@RequestPayload EmployeeDetailRequest request) {
    EmployeeDetailResponse response = new EmployeeDetailResponse();
    EmployeeEntity entity = employeeService.get(Long.toString(request.getId()));
    Employee emp = new Employee();
    emp.setId(entity.getId());
    emp.setFirstName(entity.getFirstName());
    emp.setLastName(entity.getLastName());
    emp.setSalary(entity.getSalary());
    response.setEmployeeDetail(emp);
    return response;
  }
  

}
