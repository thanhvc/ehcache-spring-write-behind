package net.danielwind.effcaching.recipe15.domain;

public final class EmployeeEntity {

	private long id;
	private long salary;
	
	private String role;
	private String firstName;
	private String lastName;
	private String department;
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public long getSalary() {
		return salary;
	}
	
	public void setSalary(long salary) {
		this.salary = salary;
	}
	
	@Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EmployeeEntity)) {
      return false;
    }

    EmployeeEntity that = (EmployeeEntity) o;

    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) {
      return false;
    }
    
    if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) {
      return false;
    }
    
    if (department != null ? !department.equals(that.department) : that.department != null) {
      return false;
    }
    
    if (role != null ? !role.equals(that.role) : that.role != null) {
      return false;
    }
    
    if (salary != that.salary) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = 31 * (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (department != null ? department.hashCode() : 0);
    result = 31 * result + (role != null ? role.hashCode() : 0);
    return result;
  }
	
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		sb.append("ID: " + this.id);
		sb.append(",");
		sb.append("First Name: " + this.firstName);
		sb.append(",");
		sb.append("Last Name: " + this.lastName);
		sb.append(",");
		sb.append("Department: " + this.department);
		sb.append(",");
		sb.append("Role: " + this.role);
		sb.append(",");
		sb.append("Salary: " + this.salary);
		sb.append("]");
		
		return sb.toString();
	}
}