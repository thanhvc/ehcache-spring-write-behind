####EhCache Spring How to
##Recipe 15 (Spring Cache Abstraction)

In this recipe you will be shown how to use built-in EhCache support in recent cache abstraction API in Spring 3.1 and higher. 
We will be using an annotation-based approach but will follow the traditional EhCache XML configuration. 

Using hsqldb 2.3.3 version to support fully JDBC 3.0

Build Dependencies
-------

| Requirement      |  Version   |
|------------------|:----------:|
|  Apache Maven    |    3.x     |
|  Java JDK        |    >= 6    |
|  Eclipse         | >= Helios  |
|  hsqldb          | >= 2.0.x  |


Building The Recipe
-------
1. Compile: mvn clean jetty:run
2. goto http://localhost:8080/recipe15/
3. Insert Employee: Clicks on Insert button
4. Gets the Employee by Id: Fill Id in textbox and Clicks on Load button
5. Implements

     net.danielwind.effcaching.recipe15.controllers.ApplicationContextProvider
            
Allowing you take the bean by this one.
6. Implements the write-behind pattern by ehcache.xml

         <cacheWriter writeMode="write-behind"
			notifyListenersOnException="true" maxWriteDelay="30"
			rateLimitPerSecond="20" writeCoalescing="true" writeBatching="true"
			writeBatchSize="10" retryAttempts="20" retryAttemptDelaySeconds="60">
			<cacheWriterFactory
				class="net.danielwind.effcaching.recipe15.cache.EmployeeCacheWriterFactory" />
		</cacheWriter>
		
		And get Bean: EmployeeDao employeeDaoImpl = (EmployeeDao) ApplicationContextProvider.getApplicationContext().getBean("employeeDaoImpl");
		
rateLimitPerSecond: The maximum number of store operations to allow per second.

7. Implements org.springframework.jdbc.support.incrementer.HsqlMaxValueIncrementer

    HsqlMaxValueIncrementer incrementer = new HsqlMaxValueIncrementer();
    incrementer.setDataSource(dataSource);
    incrementer.setIncrementerName("employee_seq");
    incrementer.setColumnName("value");
    incrementer.setCacheSize(3);
    incrementer.setPaddingLength(3);
    incrementer.afterPropertiesSet();

