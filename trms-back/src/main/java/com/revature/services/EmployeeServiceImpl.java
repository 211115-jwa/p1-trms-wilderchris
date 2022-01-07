package com.revature.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.EmployeeDAO;
import com.revature.data.EventTypeDAO;
import com.revature.data.GradingFormatDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.data.StatusDAO;
import com.revature.utils.DAOFactory;

import io.javalin.plugin.json.JsonMapper;

public class EmployeeServiceImpl implements EmployeeService {
	private EventTypeDAO eventTypeDao = DAOFactory.getEventTypeDAO();
	private GradingFormatDAO gradFormatDao = DAOFactory.getGradingFormatDAO();
	private StatusDAO statusDao = DAOFactory.getStatusDAO();
	private ReimbursementDAO reqDao = DAOFactory.getReimbursementDAO();
	private CommentDAO commentDao = DAOFactory.getCommentDAO();
	private EmployeeDAO empDao = DAOFactory.getEmployeeDAO();
	private static Logger log = LogManager.getLogger(EmployeeServiceImpl.class);
	

	@Override
	public Map<String, Set<Object>> getRequestOptions() {
		Map<String,Set<Object>> requestOptions = new HashMap<>();
		requestOptions.put("eventTypes", eventTypeDao.getAll());
		requestOptions.put("gradingFormats", gradFormatDao.getAll());
		return requestOptions;
	}

	@Override
	public int submitReimbursementRequest(Reimbursement request) {
		Status initialStatus = statusDao.getById(4);
		log.info("intialStatus var value:  " + initialStatus);
		request.setStatus(initialStatus);
		request.setSubmittedAt(LocalDateTime.now());
		return reqDao.create(request);
	}

	@Override
	public Set<Reimbursement> getReimbursementRequests(Employee requestor) {
		Set<Reimbursement> requests = reqDao.getByRequestor(requestor);
		requests.forEach(req -> {
			req.setRequestor(requestor);
		});
		return requests;
	}

	@Override
	public Set<Comment> getComments(Reimbursement request) {
		Set<Comment> comments = commentDao.getByRequestId(request.getReqId());
		comments.forEach(comment -> {
			comment.setApprover(empDao.getById(comment.getApprover().getEmpId()));
			comment.setRequest(request);
		});
		return comments;
	}

	@Override
	public int addComment(Comment comment) {
		comment.setSentAt(LocalDateTime.now());
		return commentDao.create(comment);
	}

	@Override
	public Employee getEmployeeById(int empId) {
		return empDao.getById(empId);
	}
	class JacksonMapper implements JsonMapper {
		ObjectMapper om = new ObjectMapper();
		@Override
	    public String toJsonString(Object obj) {
	        try {
				return om.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
	        return null;
	    }
	    @Override
	    public <T> T fromJsonString(String json, Class<T> targetClass) {
	        try {
				return om.readValue(json, targetClass);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        return null;
	    }
	}
}
