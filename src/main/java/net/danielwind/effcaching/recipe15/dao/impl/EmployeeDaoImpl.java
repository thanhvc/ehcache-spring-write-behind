package net.danielwind.effcaching.recipe15.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import net.danielwind.effcaching.recipe15.dao.EmployeeDao;
import net.danielwind.effcaching.recipe15.domain.EmployeeEntity;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.incrementer.HsqlMaxValueIncrementer;
import org.springframework.stereotype.Repository;

@Repository("employeeDaoImpl")
public class EmployeeDaoImpl extends JdbcDaoSupport implements EmployeeDao {

	private static final Logger log = Logger.getLogger(EmployeeDaoImpl.class);
	private final HsqlMaxValueIncrementer incrementer;
	
	@Autowired
	public EmployeeDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
		incrementer = new HsqlMaxValueIncrementer();
    incrementer.setDataSource(dataSource);
    incrementer.setIncrementerName("employee_seq");
    incrementer.setColumnName("value");
    incrementer.setCacheSize(3);
    incrementer.setPaddingLength(3);
    incrementer.afterPropertiesSet();
	}
	
	public HsqlMaxValueIncrementer getIncrementer() {
    return incrementer;
  }
	
	/**
	 * @{inheritDoc}
	 */
	@Cacheable("employeeCache")
	public List<EmployeeEntity> findAll() {
		log.info("--- Accessing Dao Layer: EmployeeDaoImpl.findAll() ---");
		String sql = "SELECT * FROM employees";
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper<EmployeeEntity>(EmployeeEntity.class));
		
	}
	
  @CacheEvict(value="employeeCache", allEntries=true)
	public EmployeeEntity insert(final EmployeeEntity emp) {
	  final String sql = "INSERT INTO EMPLOYEES " +
	      "(FIRSTNAME, LASTNAME, ROLE, DEPARTMENT, SALARY) VALUES (?, ?, ?, ?, ?)";
	  
    
	  KeyHolder keyHolder = new GeneratedKeyHolder();
	  getJdbcTemplate().update(
	      new PreparedStatementCreator() {
	          public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	              PreparedStatement ps = connection.prepareStatement(sql, new String[] {"ID"});
	              ps.setString(1, emp.getFirstName());
	              ps.setString(2, emp.getLastName());
	              ps.setString(3, emp.getRole());
	              ps.setString(4, emp.getDepartment());
	              ps.setLong(5, emp.getSalary());
	              return ps;
	          }
	      },
	      keyHolder);
	  //set key back to domain
	  emp.setId(keyHolder.getKey().longValue());
	  return emp;
	}

  @Cacheable(value="employeeCache", key="#employeeId")
  public EmployeeEntity get(String employeeId) {
    log.info("--- Accessing Dao Layer: EmployeeDaoImpl.get() ---");
    String sql = "SELECT * FROM employees WHERE ID = ?";
    return getJdbcTemplate().queryForObject(sql, new Object[] { employeeId}, new BeanPropertyRowMapper<EmployeeEntity>(EmployeeEntity.class));
  }
  
  

}
