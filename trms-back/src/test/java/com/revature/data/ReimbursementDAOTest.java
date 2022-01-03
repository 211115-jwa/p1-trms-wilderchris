package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.Employee;
import com.revature.beans.EventType;
import com.revature.beans.GradingFormat;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.utils.DAOFactory;

public class ReimbursementDAOTest {
	ReimbursementDAO remDAO = DAOFactory.getReimbursementDAO();
	private static Reimbursement rem;
	
	static Employee emp = new Employee();
	static LocalDate ld;
	static GradingFormat gf;
	static LocalTime lt;
	static EventType et;
	static Status st;
	
	@BeforeAll
	public static void mockEventTypeSetup() {
		et = new EventType();
		et.setEventId(1);
		et.setName("Other");
		et.setPercentCovered(30.0);
	}
	
	@BeforeAll
	public static void mockGradingFormatSetup() {
		gf = new GradingFormat();
		gf.setFormatId(1);
		gf.setName("Awarded");
		gf.setExample("A");
	}
	
	@BeforeAll
	public static void mockStatusSetup() {
		st = new Status();
		st.setStatusId(2);
		st.setName("Pending Approval");
		st.setApprover("Direct Supervisor");
	}
	
	
	
	@BeforeAll
	public static void mockReimbursementSetup() {// mock bike
		rem = new Reimbursement();
		
		rem.setRequestor(emp);
		rem.setEventDate(LocalDate.of(2000,01,01));
		rem.setEventTime(LocalTime.of(00,01,00));
		rem.setLocation("location string");
		rem.setDescription("test description for mock");
		rem.setCost(100);
		rem.setGradingFormat(gf);
		rem.setEventType(et);
		rem.setStatus(st);
		rem.setSubmittedAt(LocalDateTime.now());
	}
	
	
	
	
	@Test
	public void testGetByRequestor() {
		Set<Reimbursement> rem = remDAO.getByRequestor(emp);
		//System.out.println(rem);
		assertNotEquals(null, rem);
	}
	@Test
	public void testGetByStatus() {
		Set<Reimbursement> rem = remDAO.getByStatus(st);
			assertNotEquals(null,rem);
	}
	@Test
	public void testCreateReimbursement() {
	System.out.println(st+ "  " +gf+ "  "+ et);
		
		int test = remDAO.create(rem);
		System.out.println(test + "   " + rem);
		assertNotEquals(0,test);
	}
	@Test
	public void testUpdateReimbursement() {
		String descriptionUpdate = "test Description Update";
		rem.setDescription(descriptionUpdate);
		remDAO.update(rem);
		rem = remDAO.getById(rem.getReqId());
		assertEquals(descriptionUpdate, rem.getDescription());
	}
	@Test
	public void testDeleteReimbursement() {
		remDAO.delete(rem);
		assertEquals(null,remDAO.getById(rem.getReqId()));
	}
	@Test
	public void testGetAllReimbursement() {
		
	}
	@Test
	public void testGetByIdReimbursement() {
		
	}
}
