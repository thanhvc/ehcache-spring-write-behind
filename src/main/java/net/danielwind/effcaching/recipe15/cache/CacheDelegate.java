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

import java.util.List;

import net.danielwind.effcaching.recipe15.dao.EmployeeDao;
import net.danielwind.effcaching.recipe15.domain.EmployeeEntity;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * May 12, 2015  
 */
@Repository("cacheDelegate")
public final class CacheDelegate {

  private static final Logger log = Logger.getLogger(CacheDelegate.class);
  private static final String EHCACHE_CONFIG = "src/main/resources/ehcache.xml";
  private static final String CACHE_NAME = "employeeCache";
  
  private CacheManager manager;
  private Ehcache cache;

  @Autowired
  private EmployeeDao employeeDaoImpl;
  
  /**
   * Default Constructor
   */
  
  public CacheDelegate() {
    log.info("--- Initializing Cache Delegate Class... ---");
    manager = CacheManager.create(EHCACHE_CONFIG);
    cache = manager.getCache(CACHE_NAME);
    EmployeeCacheWriter writer = new EmployeeCacheWriter(cache);
    cache.registerCacheWriter(writer);
    EmployeeCacheLoader loader = new EmployeeCacheLoader();
    cache.registerCacheLoader(loader);
  }
  
  /**
   * Method that removes a certain object (by key) from cache.
   * Please note that this method will not log until the object
   * gets evicted from data store.
   * @param key The cache object key
   */
  public void removeElementFromCache(Object key) {
    if(!cache.remove(key)){
      throw new RuntimeException("Could Not remove key-value from Cache");
    }
  }
  
  /**
   * Method that adds a new employee object in Cache
   * @param employee Domain object instance
   */
  public void addElementToCache(EmployeeEntity emp) {
    cache.put(new Element(emp.getId(), emp));
  }
  
  /**
   * Method that adds a new employee object in CacheWriter
   * @param employee Domain object instance
   */
  public void addElementToCacheWriter(EmployeeEntity emp) {
    log.info("--- CacheDelegate.addElementToCacheWriter() ---");
    log.info("before::cache size = " + cache.getSize());
    //get key
    long key = employeeDaoImpl.getIncrementer().nextLongValue();
    emp.setId(key);
    //put caching
    cache.putWithWriter(new Element(emp.getId(), emp));
    log.info("after::cache size = " + cache.getSize());
  }
  
  /**
   * Method that get a employee object in Cache
   * @param employee Domain object instance
   */
  public EmployeeEntity getElementFromCache(Long key) {
    log.info("cache size = " + cache.getSize());
    return (EmployeeEntity) cache.get(key).getObjectValue();
  }
  
  /**
   * Method that get a employee object in CacheLoader
   * @param employee Domain object instance
   */
  public EmployeeEntity getElementFromCacheLoader(Long key) {
    log.info("cache size = " + cache.getSize());
    return (EmployeeEntity) cache.getWithLoader(key, null, null) .getObjectValue();
  }
  
  /**
   * Method that removes all Elements in Cache
   */
  public void removeAllElementsInCache() {
    cache.removeAll();
  }

  /**
   * Find all the employee
   * @return The list
   */
  public List<EmployeeEntity> findAll() {
    log.info("===============CacheDelegate#findAll()==============");
    log.info(employeeDaoImpl);
    return employeeDaoImpl.findAll();
  }
  
  /**
   * Simulates an exception during a cache method
   * execution. Invalid key.
   */
  public void generateException() {
    cache.getCacheExceptionHandler().onException(cache, 100, new CacheException());
  }
}
