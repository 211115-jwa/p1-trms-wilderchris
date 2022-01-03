package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.Role;
import com.revature.data.postgres.EmployeePostgres;

@TestMethodOrder(OrderAnnotation.class)
public class EmployeeDAOTest {

	private static String unUpdate = "Username Updated";
	private static EmployeeDAO empDAO = new EmployeePostgres();
	private static Employee mockEmp = new Employee();
	private static Role role;
	private static Department dept = new Department();
	private static Employee sup = mockEmp;
	
	@BeforeAll
	public static void mockRoleSetup() {// mock
		role = new Role();
		role.setRoleId(19);
		role.setName("fired");
	}
	
	@BeforeAll
	public static void mockEmployeeSetup() {// mock 
		mockEmp = new Employee();
		mockEmp.setFirstName("first");
		mockEmp.setLastName("last");
		mockEmp.setUsername("Huffy");
		mockEmp.setPassword("xxl password");
		mockEmp.setRole(role);
		mockEmp.setFunds(1000);
		mockEmp.setSupervisor(sup);
		mockEmp.setDepartment(dept);
	
	}	
	@Test
	@Order(1)
	public void testCreate() {
		int genId = empDAO.create(mockEmp);
		assertNotEquals(0, genId);
	}
	@Test
	@Order(2)
	public void testUpdate() {
		
		mockEmp.setUsername(unUpdate);
		empDAO.update(mockEmp);
		assertEquals(unUpdate, empDAO.getByUsername(unUpdate).getUsername());
	}
	@Test
	@Order(3)
	public void testDelete() {
		
		empDAO.delete(mockEmp);
		assertEquals(null,empDAO.getByUsername(unUpdate));
	}
	
	@Test
	public void testGetAll() {
		Set<Employee> emps = empDAO.getAll();
		
		assertNotEquals(null, emps);
	}
	
}
