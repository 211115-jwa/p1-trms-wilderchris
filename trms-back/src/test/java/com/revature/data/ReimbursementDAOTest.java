package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Role;
import com.revature.beans.Status;
import com.revature.utils.DAOFactory;
@TestMethodOrder(OrderAnnotation.class)
public class ReimbursementDAOTest {
	
	// Tests All Green
	
	
	//private static Logger log = LogManager.getLogger(ReimbursementDAOTest.class);
	ReimbursementDAO remDAO = DAOFactory.getReimbursementDAO();
	private static Reimbursement mockRem;
	
	private static int genId;
	private static Employee mockEmp;
	private static GradingFormat gf;
	private static EventType et;
	private static Status status;
	private static Role role;
	private static Employee sup;
	private static Department dept;
	
	
	@BeforeAll
	public static void mockReimbursementSetup() {// mock req
		mockRem = new Reimbursement();
		mockRem.setRequestor(mockEmp);
		mockRem.setEventDate(LocalDate.of(2000,01,01));
		mockRem.setEventTime(LocalTime.of(11,11,11));
		mockRem.setLocation("location string");
		mockRem.setDescription("test description for mock");
		mockRem.setCost(1);
		mockRem.setGradingFormat(gf);
		mockRem.setEventType(et);
		mockRem.setStatus(status);
		mockRem.setSubmittedAt(LocalDateTime.now());
	}
	@BeforeAll
	public static void mockEventTypeSetup() {
		et = new EventType();
		et.setEventId(1);
		et.setName("Other");
		et.setPercentCovered(30.0);
	}
	@BeforeAll
	public static void mockStatusSetup() {
		status = new Status();
		status.setStatusId(2);
		status.setName("Pending Approval");
		status.setApprover("Direct Supervisor");
	}	
	@BeforeAll
	public static void mockGradingFormatSetup() {
		gf = new GradingFormat();
		gf.setFormatId(1);
		gf.setName("Awarded");
		gf.setExample("A");
	}
	
		
	@BeforeAll
	public static void mockEmployeeSetup() {// mock emp
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
	@BeforeAll
	public static void mockRoleSetup() {// mock
		role = new Role();
		role.setRoleId(19);
		role.setName("fired");
	}
	
	
	@Test
	@Order(1)
	public void testCreateReimbursement() {
		
		//log.info("status :  " + status + " gf: " +gf+ "  et:"+ et);
		mockEmp.setEmpId(15);
		mockRem.setGradingFormat(gf);
		mockRem.setStatus(status);
		//log.info(" mockEmp:  " + mockRem);
		mockRem.setRequestor(mockEmp);
		genId = remDAO.create(mockRem);
		
		assertNotEquals(0,genId);
	}// create PosTest  1
	
	@Test
	public void testGetByIdReimbursementGood() {
		int testId = 5;
		Reimbursement r = remDAO.getById(testId);
		//log.info(r);
		assertNotEquals(null, r);
	}//PositiveTest get by id 2
	
	
	@Test			
	public void testGetByIdReimbursementInvalidId() {
		int testId = 100;
		Reimbursement r = remDAO.getById(testId);
		//log.info(r);
		assertEquals(null, r);
	}// NegativeTest for get by id 2
	
	@Test
	public void testGetAllReimbursement() {
		Set<Reimbursement> allReqs = remDAO.getAll();
		//log.info(allReqs);
		assertNotEquals(null, allReqs);
	}// get all PosTest for 3
	
	@Test
	@Order(2)
	public void testUpdateReimbursement() {
		mockRem.setReqId(genId);
		mockRem.setGradingFormat(gf);
		mockRem.setStatus(status);
		mockRem.setRequestor(mockEmp);
		String descriptionUpdate = "test Description Update";
		mockRem.setDescription(descriptionUpdate);
		//log.info("To be added to update: " +descriptionUpdate+  " :" + mockRem);
		remDAO.update(mockRem);
		mockRem = remDAO.getById(mockRem.getReqId());
		assertEquals(descriptionUpdate, mockRem.getDescription());
	}// update PosTest 4
	
	
	@Test
	@Order(3)//
	public void testDeleteReimbursement() {
		//log.info(mockRem);
		mockRem.setReqId(genId);
		remDAO.delete(mockRem);
		assertEquals(null,remDAO.getById(mockRem.getReqId()));
	}// delete PosTest for 5
	
	
	@Test
	public void testGetByRequestor() {
		Set<Reimbursement> rem = remDAO.getByRequestor(mockEmp);
		//log.info(mockRem);
		assertNotNull(rem);
	}	//PosTest for 6
	
	@Test
	public void testGetByRequestorInvalidEmp() {
		mockEmp.setEmpId(100);
		Set<Reimbursement> rem = remDAO.getByRequestor(mockEmp);
		//log.info(mockRem);
		assertTrue(rem.isEmpty());
	}//NegativeTest for 6
	
	
	@Test
	public void testGetByStatus() {
		Set<Reimbursement> rem = remDAO.getByStatus(status);
			assertNotEquals(null,rem);
	}//PosTest for 7
	
	@Test
	public void testGetByStatusInvalidStatus() {
		status.setStatusId(100);
		Set<Reimbursement> rem = remDAO.getByStatus(status);
			assertTrue(rem.isEmpty());
	}// NegativeTest for 7
	
	
	
		
}
