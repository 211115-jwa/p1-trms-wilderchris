package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	// Tests all Green
	
	
	//private static Logger log = LogManager.getLogger(EmployeeDAOTest.class);
	//private static int next = 30;
	private static String username = "rmunro9";
	private static int genId;
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
	public void testCreate() {// 1
		mockEmp.setRole(role);
		genId = empDAO.create(mockEmp);
		assertNotEquals(0, genId);
	}

	@Test
	public void testGetById() {// 2
		int testId = 20;
		Employee e = empDAO.getById(testId);
		// log.info(e);
		assertNotEquals(null, e);
	}

	@Test
	public void testGetByIdInvalidId() {// NegTest 2
		int testId = 100;
		Employee e = empDAO.getById(testId);
		// log.info(e);
		assertEquals(null, e);
	}

	@Test
	public void testGetAll() {// 3
		Set<Employee> allEmps = empDAO.getAll();
		// log.info(allEmps);
		assertNotEquals(null, allEmps);

	}

	@Test
	@Order(2)
	public void testUpdate() {// 4

		mockEmp.setRole(role);
		//log.info(genId);
		mockEmp.setEmpId(genId);
		mockEmp.setUsername(unUpdate);
		empDAO.update(mockEmp);
		assertEquals(unUpdate, empDAO.getByUsername(unUpdate).getUsername());
	}

	@Test
	@Order(3)
	public void testDelete() {// 5

		//log.info(mockEmp);
		//log.info(genId);
		mockEmp.setEmpId(genId);
		empDAO.delete(mockEmp);
		assertEquals(null, empDAO.getById(mockEmp.getEmpId()));
	}

	@Test
	public void testGetByUserName() {// PosTest 6
		Employee e = empDAO.getByUsername(username);
		// log.info(e);
		assertNotNull(e);
	}

	@Test
	public void testGetByUserNameUsernameInvalid() {// NegTest 6
		Employee e = empDAO.getByUsername(username + "xx");
		// log.info(e);
		assertEquals(null, e);
	}
}
