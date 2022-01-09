package com.revature.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.utils.DAOFactory;

public class RequestReviewServiceImpl implements RequestReviewService {

	private static EmployeeDAO empDAO = DAOFactory.getEmployeeDAO();
	private static ReimbursementDAO remDAO = DAOFactory.getReimbursementDAO();
	private static StatusDAO statDAO = DAOFactory.getStatusDAO();
	private static CommentDAO comDAO = DAOFactory.getCommentDAO();
	static Logger log = LogManager.getLogger(RequestReviewServiceImpl.class);

	@Override
	public Set<Reimbursement> getPendingReimbursements(Employee approver) {
		Set<Reimbursement> sup = new HashSet<>();

		if (7 <= approver.getRole().getRoleId() || approver.getRole().getRoleId() <= 11) {
			Set<Status> stats = statDAO.getByName("Pending Sup");
			log.info(stats);
			List<Status> s = new ArrayList<>(stats);

			sup = remDAO.getByStatus(s.get(0));
			log.info(sup);
		} else if (1 <= approver.getRole().getRoleId() || approver.getRole().getRoleId() <= 5) {
			Set<Status> stats = statDAO.getByName("Pending DH");
			List<Status> s = new ArrayList<>(stats);
			sup = remDAO.getByStatus(s.get(0));
		} else if (approver.getRole().getRoleId() == 6) {
			Set<Status> stats = statDAO.getByName("Pending BC");
			List<Status> s = new ArrayList<>(stats);
			sup = remDAO.getByStatus(s.get(0));
		}

		Set<Reimbursement> sts = new HashSet<>(sup);
		return sts;
	}

	@Override
	public void approveRequest(Reimbursement request) {// should be good
		Comment com = new Comment();

		com.setApprover(request.getRequestor().getSupervisor());
		com.setRequest(request);
		com.setSentAt(LocalDateTime.now());

		String approver = getApproverString(request);

		if (5 > request.getStatus().getStatusId() && request.getStatus().getStatusId() > 1) {
			com.setCommentText(" ****Approved****\r\n" + " *****System Generated*****\r\n " + "****Approved by "
					+ approver + "****\r\n");
			request.getStatus().setStatusId(request.getStatus().getStatusId() - 1);// approves request
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
			remDAO.update(request);// updates the request
		}
		double fund = request.getRequestor().getFunds() - costCalc(request);
		Employee e = request.getRequestor();
		e.setFunds(fund);
		empDAO.update(e);
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

		com.setApprover(request.getRequestor().getSupervisor());
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

	private static double costCalc(Reimbursement r) {
		double c = r.getCost();
		int etp = r.getEventType().getEventId();
		
		if (etp == 1 ){
		    c = c * .80;
		}else if (etp == 2){
		    c = c * .6;
		}else if (etp == 3){
		    c = c * .75;
		}else if (etp == 4){
		    c = c * 1;
		}else if (etp == 5){
		    c = c * .9;
		}else 
		    c = c * .3;
		return c;
	}
	
}
