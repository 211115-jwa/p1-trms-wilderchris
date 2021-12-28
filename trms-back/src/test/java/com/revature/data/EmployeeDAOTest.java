package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import javax.management.relation.Role;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.data.postgres.EmployeePostgres;

public class EmployeeDAOTest {

	private static EmployeeDAO empDAO = new EmployeePostgres();
	private static Employee mockEmp = new Employee();
	private static Object role = new Role(null, null);
	private static Department dept = new Department();
	private static Employee sup = new Employee();
	
	@BeforeAll
	public static void mockEmployeeSetup() {// mock bike
		mockEmp = new Employee();
		mockEmp.setFirstName("mock");
		mockEmp.setLastName("mountain");
		mockEmp.setUsername("Huffy");
		mockEmp.setPassword("XXL");
		mockEmp.setRole((com.revature.beans.Role) role);
		mockEmp.setFunds(1000);
		mockEmp.setSupervisor(sup);
		mockEmp.setDepartment(dept);
	
	}	
	@Test
	public void createTest() {
		Employee emp = new Employee();
		int genId = empDAO.create(emp);
		assertNotEquals(0,genId);
	}
}
