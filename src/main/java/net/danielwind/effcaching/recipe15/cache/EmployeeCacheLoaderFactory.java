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

import java.util.Properties;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.loader.CacheLoader;
import net.sf.ehcache.loader.CacheLoaderFactory;


/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * May 13, 2015  
 */
public class EmployeeCacheLoaderFactory extends CacheLoaderFactory {

  @Override
  public CacheLoader createCacheLoader(Ehcache arg0, Properties arg1) {
    return new EmployeeCacheLoader();
  }

  

}
