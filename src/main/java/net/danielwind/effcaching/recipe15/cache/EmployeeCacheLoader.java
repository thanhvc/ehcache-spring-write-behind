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
package net.danielwind.effcaching.recipe15.cache;

import java.util.Collection;
import java.util.Map;

import net.danielwind.effcaching.recipe15.controllers.ApplicationContextProvider;
import net.danielwind.effcaching.recipe15.dao.EmployeeDao;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Status;
import net.sf.ehcache.loader.CacheLoader;

import org.apache.log4j.Logger;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * May 13, 2015  
 */
public class EmployeeCacheLoader implements CacheLoader {
  
  private static final Logger log = Logger.getLogger(EmployeeCacheLoader.class);

  @Override
  public CacheLoader clone(Ehcache arg0) throws CloneNotSupportedException {
    return null;
  }

  @Override
  public void dispose() throws CacheException {
    
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public Status getStatus() {
    return null;
  }

  @Override
  public void init() {
    
  }

  @Override
  public Object load(Object employeeId) throws CacheException {
    log.info("==========EmployeeCacheLoader#load(employeeId)==========");
    EmployeeDao employeeDaoImpl = (EmployeeDao) ApplicationContextProvider.getApplicationContext().getBean("employeeDaoImpl");
    return employeeDaoImpl.get(Long.toString((Long)employeeId));
  }

  @Override
  public Object load(Object arg0, Object arg1) {
    log.info("==========EmployeeCacheLoader#load(arg0, arg1)==========");
    return null;
  }

  @Override
  public Map loadAll(Collection arg0) {
    log.info("==========EmployeeCacheLoader#loadAll(arg0)==========");
    return null;
  }

  @Override
  public Map loadAll(Collection arg0, Object arg1) {
    log.info("==========EmployeeCacheLoader#loadAll(arg0, arg1)==========");
    return null;
  }

}
