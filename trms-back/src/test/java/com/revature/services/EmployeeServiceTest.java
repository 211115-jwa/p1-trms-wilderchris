package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.revature.beans.Comment;
import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.controllers.RequestsController;
import com.revature.data.CommentDAO;
import com.revature.data.DepartmentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.EventTypeDAO;
import com.revature.data.GradingFormatDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.utils.DAOFactory;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	private static Logger log = LogManager.getLogger(EmployeeServiceTest.class);
	
	
	@Mock
	private CommentDAO comDAO = DAOFactory.getCommentDAO();

	@Mock
	private DepartmentDAO dd = DAOFactory.getDepartmentDAO();

	@Mock
	private EmployeeDAO empDAO = DAOFactory.getEmployeeDAO();

	@Mock
	private EventTypeDAO etDAO = DAOFactory.getEventTypeDAO();

	@Mock
	private GradingFormatDAO gfDAO = DAOFactory.getGradingFormatDAO();

	@Mock
	private ReimbursementDAO reqDAO = DAOFactory.getReimbursementDAO();

	@Mock
	private StatusDAO statDAO = DAOFactory.getStatusDAO();

	@InjectMocks
	private EmployeeService empServ = new EmployeeServiceImpl();

	private static Set<Employee> mockEmployees;
	private static Set<Reimbursement> mockRequests;
	private static Set<Comment> mockComments;
	//private static Set<Department> mockDepartments;

	Department mockDept = new Department(1, "Test Department", 24);

	@BeforeAll
	public static void mockRequestsSetup() {
		mockRequests = new HashSet<>();

		for (int i = 1; i <= 8; i++) {
			Reimbursement req = new Reimbursement();
			req.setReqId(i);
			req.getRequestor().setEmpId(i);
			req.getStatus().setStatusId(i);
			mockRequests.add(req);
		}
	}

	@BeforeAll
	public static void mockCommentsSetup() {
		mockComments = new HashSet<>();

		for (int i = 1; i <= 24; i++) {
			Comment com = new Comment();
			com.setCommentId(i);
			for (int j = 1; i <= 8; i++) {
				com.setRequest(new Reimbursement());
				com.getRequest().setReqId(j);
				com.setApprover(new Employee());
				if (i < 3)
					com.getApprover().setEmpId(1);
				else if (i < 5)
					com.getApprover().setEmpId(23);
				else
					com.getApprover().setEmpId(24);
			}
			mockComments.add(com);
		}
	}

	@BeforeAll
	public static void mockEmployeesSetup() {
		mockEmployees = new HashSet<>();

		for (int i = 1; i <= 24; i++) {
			Employee emp = new Employee();
			emp.setEmpId(i);
			emp.setFirstName("Test" + i);
			emp.setLastName("Test" + i);
			emp.setUsername("test" + i);
			emp.setPassword("pass");
			emp.setFunds(1000.00);
			emp.getSupervisor().setEmpId(24);
			if (i == 24)
				emp.getRole().setRoleId(3);
			else if (i == 23)
				emp.getRole().setRoleId(2);
			else
				emp.getRole().setRoleId(1);
			mockEmployees.add(emp);
		}
	}

	
	
//	@Test
//	public void testSubmitRemimbursementRequest() {//
//		List<Reimbursement> rem = new ArrayList<>(mockRequests);
//		when(reqDAO.create(rem.get(1))).thenReturn(1);
//		int result = empServ.submitReimbursementRequest(rem.get(1));
//		assertNotEquals(0, result);
//		
//	}
}
