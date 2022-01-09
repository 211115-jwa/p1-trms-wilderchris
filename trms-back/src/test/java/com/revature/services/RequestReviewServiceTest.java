package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Comment;
import com.revature.beans.Department;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.CommentDAO;
import com.revature.data.DepartmentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.EventTypeDAO;
import com.revature.data.GradingFormatDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.utils.DAOFactory;


@ExtendWith(MockitoExtension.class)
public class RequestReviewServiceTest {
	
	//private static Logger log = LogManager.getLogger(RequestReviewServiceTest.class);
	
	
	@Mock
	private CommentDAO comDAO = DAOFactory.getCommentDAO();

	@Mock
	private DepartmentDAO deptDao = DAOFactory.getDepartmentDAO();

	@Mock
	private EmployeeDAO empDAO = DAOFactory.getEmployeeDAO();

	@Mock
	private EventTypeDAO eventDAO = DAOFactory.getEventTypeDAO();

	@Mock
	private GradingFormatDAO gfDAO = DAOFactory.getGradingFormatDAO();

	@Mock
	private ReimbursementDAO remDAO = DAOFactory.getReimbursementDAO();

	@Mock
	private StatusDAO statDAO = DAOFactory.getStatusDAO();

	@InjectMocks
	private RequestReviewService rrServ = new RequestReviewServiceImpl();
	private static Set<Reimbursement> mockReqs;
	private static Set<Employee> mockEmps;
	private static Set<Comment> mockComs;

	@BeforeAll
	public static void mockSetup() {
		mockReqs = new HashSet<>();
		mockComs = new HashSet<>();
		mockEmps = new HashSet<>();
		Department dep = new Department();
		dep.setDeptId(1);
		dep.setDeptHeadId(6);
		dep.setName("test");
		for (int i = 1; i < 11; i++) {
			Reimbursement rem = new Reimbursement();
			rem.setReqId(i);
			rem.getRequestor().setEmpId(i);
			rem.getStatus().setStatusId(i);
			mockReqs.add(rem);
		}
		
		for (int i = 1; i < 30; i++) {
			Comment c = new Comment();
			c.setCommentId(i);
			for (int j = 1; i < 11; i++) {
				c.setRequest(new Reimbursement());
				c.getRequest().setReqId(j);
				c.setApprover(new Employee());
				if (i < 3)
					c.getApprover().setEmpId(1);
				else if (i < 5)
					c.getApprover().setEmpId(6);
				else
					c.getApprover().setEmpId(10);
			}
			mockComs.add(c);
		}
		
		for (int i = 1; i < 30; i++) {
			Employee emp = new Employee();
			emp.setEmpId(i);
			emp.setFirstName("Mock" + i);
			emp.setLastName("Test" + i);
			emp.setUsername("mocktest" + i);
			emp.setPassword("123456");
			emp.setFunds(1000.00);
			emp.setDepartment(dep);
			emp.getSupervisor().setEmpId(10);
			if (i == 6)
				emp.getRole().setRoleId(3);
			else if (i == 10)
				emp.getRole().setRoleId(2);
			else
				emp.getRole().setRoleId(1);
			mockEmps.add(emp);
		}
		
	}
	 
	@Test
	public void testgetPendingReimbursements() {
		List<Employee> emp = new ArrayList<>(mockEmps);
		
		when(remDAO.getByRequestor(emp.get(0))).thenReturn(mockReqs);
		Set<Reimbursement> riem = rrServ.getPendingReimbursements(emp.get(1));
		
		assertTrue(!riem.isEmpty());
	}
	
	@Test
	public void testApproveRequest() {
		
	}
	@Test
	public void testRejectRequest() {
		
	}
	
	@Test
	public void testRejectRequestwithComment() {
		//List<Reimbursement> rems = new ArrayList<>(mockReqs);
		List<Comment> coms = new ArrayList<>(mockComs);
		
		when(comDAO.create(coms.get(0))).thenReturn(null);
		
		assertEquals(1,comDAO.getAll());
	}
	

}