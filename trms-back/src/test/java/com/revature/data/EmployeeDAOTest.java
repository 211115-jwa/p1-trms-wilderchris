package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import com.revature.beans.Employee;
import com.revature.data.postgres.EmployeePostgres;

public class EmployeeDAOTest {

	private static EmployeeDAO empDAO = new EmployeePostgres();
	//private static Employee mockEmp = new Employee();
	//private static com.revature.beans.Role role = new Role(null, null);
	//private static Department dept = new Department();
//	private static Employee sup = mockEmp;
	
//	@BeforeAll
//	public static void mockEmployeeSetup() {// mock bike
//		mockEmp = new Employee();
//		mockEmp.setFirstName("mock");
//		mockEmp.setLastName("mountain");
//		mockEmp.setUsername("Huffy");
//		mockEmp.setPassword("XXL");
//		com.revature.beans.Role role = null;
//		mockEmp.setRole(role);
//		mockEmp.setFunds(1000);
//		mockEmp.setSupervisor(sup);
//		mockEmp.setDepartment(dept);
//	
//	}	
//	@Test
//	public void createTest() {
//		Employee emp = new Employee();
//		int genId = empDAO.create(emp);
//		assertNotEquals(0,genId);
//	}
	@Test
	public void testGetAll() {
		Set<Employee> emps = empDAO.getAll();
		System.out.println(emps);
		assertNotEquals(null, emps);
	}
	
}
