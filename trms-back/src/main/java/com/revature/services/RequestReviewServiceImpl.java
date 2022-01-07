package com.revature.services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Bike;
import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.ReimbursementDAO;
import com.revature.utils.DAOFactory;

public class RequestReviewServiceImpl implements RequestReviewService {
	
	private static ReimbursementDAO remDAO = DAOFactory.getReimbursementDAO();
	
	
	static Logger log = LogManager.getLogger(RequestReviewService.class);
	
	@Override
	public Set<Reimbursement> getPendingReimbursements(Employee approver) {
		Set<Reimbursement> approverReims = new HashSet<>();
		
		Set<Reimbursement> rems = remDAO.getAll();
		
		for (Iterator<Reimbursement> it = rems.iterator(); it.hasNext();) {
			Reimbursement r = it.next(); 
			if ((r.getBrand().toLowerCase()).equals(brand.toLowerCase())) { // until it finds the brand
				bikes.add(r);// add it to the bike collection
			}
		}
		
		
		/**
		 * Returns the Set of reimbursement requests that are
		 * currently pending approval from the specified Employee.
		 * The method must account for all of the following scenarios:
		 * <ul>
		 * 	<li>Requests that are at the "direct supervisor" approval
		 * status and the specified employee is the requestor's direct
		 * supervisor</li>
		 * 	<li>Requests that are at the "department head" approval
		 * status and the specified employee is the head of that department</li>
		 * 	<li>Requests that are at the "benefits coordinator" approval
		 * status and the specified employee is in the human resources
		 * department</li>
		 * </ul>
		 * 
		 * @param approver the employee who must approve the returned requests
		 * @return the Set of requests awaiting the specified employee's approval
		 */
		return null;
	}

	@Override
	public void approveRequest(Reimbursement request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectRequest(Reimbursement request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectRequest(Reimbursement request, Comment comment) {
		// TODO Auto-generated method stub
		
	}

}
