package com.revature.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.data.CommentDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.utils.DAOFactory;

public class RequestReviewServiceImpl implements RequestReviewService {

	private static ReimbursementDAO remDAO = DAOFactory.getReimbursementDAO();
	//private static EmployeeDAO empDAO = DAOFactory.getEmployeeDAO();
	//private static StatusDAO statDAO = DAOFactory.getStatusDAO();
	private static CommentDAO comDAO = DAOFactory.getCommentDAO();
	static Logger log = LogManager.getLogger(RequestReviewServiceImpl.class);

	@Override
	public Set<Reimbursement> getPendingReimbursements(Employee approver) {// test more// should be good
		Set<Reimbursement> approverReims = new HashSet<>();
		Set<Reimbursement> rems = remDAO.getAll();

		for (Iterator<Reimbursement> it = rems.iterator(); it.hasNext();) {
			Reimbursement r = it.next();
			log.info(r);
			if (r.getStatus().getName().contains("Sup")
					&& approver.getEmpId() == r.getRequestor().getSupervisor().getEmpId()) {
				log.info(" the r:"+ r);
				log.info(r.getRequestor().getSupervisor().getEmpId());
				approverReims.add(r);
			} else if (r.getStatus().getName().contains("Dh")
					&& approver.getEmpId() == r.getRequestor().getSupervisor().getSupervisor().getEmpId()) {// need DH
				approverReims.add(r);
			} else if (r.getStatus().getName().contains("BC") && approver.getEmpId() == 6) {// 6 is benCo
				approverReims.add(r);
			}
		}
		log.info(approverReims);
	return approverReims;
	}

	@Override
	public void approveRequest(Reimbursement request) {// should be good
		Comment com = new Comment();

		com.setRequest(request);
		com.setSentAt(LocalDateTime.now());

		String approver = getApproverString(request);

		/// checking for logic// test comeback
//		if( approver == "Supervisor") 
//			com.setApprover(request.getRequestor().getSupervisor());
//		else if( approver == "Dept. Head")
//			com.setApprover(request.getRequestor().getSupervisor().getSupervisor());
//		else	
//			com.setApprover(request.getRequestor().getSupervisor().setEmpId();///
//			

		if (5 > request.getStatus().getStatusId() && request.getStatus().getStatusId() > 1) {
			com.setCommentText(" ****Approved****\r\n" + " *****System Generated*****\r\n " + "****Approved by "
					+ approver + "****\r\n");
			request.getStatus().setStatusId(request.getStatus().getStatusId() + 1);// approves request
			remDAO.update(request);// updates teh request
		} else if (request.getStatus().getStatusId() == 1) {// 1 is the appreoved id
			com.setCommentText(" ****Error****\r\n" + " *****System Generated*****\r\n "
					+ "****Submitted for Approval Duplicate****");// already approved req

		} else {
			com.setCommentText(" ****Rejected Reimburesment****\r\n" + " *****System Generated*****\r\n "
					+ "****not available for Aprroval****");// a rejected req, must create a new

		}

		if (request.getRequestor().getRole().getName().contains("BenCo")
				&& request.getStatus().getName().contains("BenCo")) {
			request.getStatus().setStatusId(1);// approves request
			remDAO.update(request);// updates teh request
		}

		comDAO.create(com);
	}

	@Override
	public void rejectRequest(Reimbursement request) {// test
		request.getStatus().setStatusId(5);
		Comment com = new Comment();

		com.setApprover(null);
		com.setRequest(request);
		com.setSentAt(LocalDateTime.now());

		String approver = getApproverString(request);

		com.setCommentText(" ****Rejected by " + approver + "****\r\n" + " *****System Generated*****\r\n "
				+ "****Must Resubmit****\r\n" + com.getCommentText());
		comDAO.create(com);

	}

	@Override
	public void rejectRequest(Reimbursement request, Comment comment) {// test
		request.getStatus().setStatusId(5);
		String approver = getApproverString(request);
		comment.setRequest(request);
		comment.setCommentText(" ****Rejected by " + approver + "****\r\n" + " *****System Generated*****\r\n "
				+ "****Must Resubmit****\r\n" + comment.getCommentText());
		comDAO.create(comment);
	}

	private static String getApproverString(Reimbursement request) {
		String approver = "";
		if (request.getStatus().getName().contains("Sup"))
			approver = "Supervisor";
		else if (request.getStatus().getName().contains("DH"))
			approver = "Dept. Head";
		else if (request.getStatus().getName().contains("BC"))
			approver = "BenCo";

		return approver;
	}

}
