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
package net.danielwind.effcaching.recipe15.dao.cached;

import java.util.List;

import net.danielwind.effcaching.recipe15.cache.CacheDelegate;
import net.danielwind.effcaching.recipe15.dao.EmployeeDao;
import net.danielwind.effcaching.recipe15.domain.EmployeeEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Repository;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * May 12, 2015  
 */
@Repository("cachedEmployeeDao")
public class CachedEmployeeDao implements EmployeeDao {

  @Autowired
  private CacheDelegate cacheDelegate;
  
  @Override
  public List<EmployeeEntity> findAll() {
    return cacheDelegate.findAll();
  }

  @Override
  public EmployeeEntity insert(EmployeeEntity emp) {
    cacheDelegate.addElementToCacheWriter(emp);
    return emp;
  }

  @Override
  public EmployeeEntity get(String employeeId) {
    return cacheDelegate.getElementFromCacheLoader(Long.parseLong(employeeId));
  }
  
  @Override
  public DataFieldMaxValueIncrementer getIncrementer() {
    throw new UnsupportedOperationException("Unsupported get HsqlMaxValueIncrementer");
  }

}
