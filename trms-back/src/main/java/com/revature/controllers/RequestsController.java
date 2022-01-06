package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.services.EmployeeService;
import com.revature.services.EmployeeServiceImpl;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class RequestsController {
//	ObjectMapper objectMapper = 
//		    new ObjectMapper().registerModule(new JavaTimeModule())
//		            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//	
//	ObjectMapper jackson = new ObjectMapper();
//	jackson.registerModule(new JavaTimeModule());
//	jackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//	JavalinJackson.configure(jackson);

	private static EmployeeService empServ = new EmployeeServiceImpl();
	private static Logger log = LogManager.getLogger(RequestsController.class);
	
	/**
	 * Retrieves the submitted reimbursement request from the
	 * HTTP request body and sends it to be inserted in the database.
	 * <p>
	 * If the insertion is not successful, sends an HTTP response
	 * with a status code of 400 (Bad Request) and a message stating
	 * that something went wrong.
	 * <p>
	 * If the insertion is successful, sends an HTTP response with
	 * a status code of 201 (Created) and the submitted request with
	 * its newly generated ID.
	 * <p>
	 * This method should be handling a POST request.
	 * 
//	   InputStream is = ctx.bodyAsInputStream();
//		ObjectInputStream ois = null;
//		log.info("submitting request object: " + is + ",   :");
//		Reimbursement request = (Reimbursement) ois.readObject();
//		log.info("submitting request object: " + request + ",   :");
//		ois.close();
//		is.close();
//		
//		try {
//			ois = new ObjectInputStream(is);
//		} catch (NullPointerException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
 * 
	 * @param ctx Javalin's Context object representing the HTTP request and response
	 */
	public static void submitReimbursementRequest(Context ctx) {
		
		
		Reimbursement request = ctx.bodyAsClass(Reimbursement.class);
				
		log.info("submitting request object: " + request + ",   :");
		ctx.result(" nothing set up to view");
		int reqId = empServ.submitReimbursementRequest(request);
		if (reqId != 0) {
			ctx.status(HttpCode.CREATED);
			request.setReqId(reqId);
			ctx.json(request);
		} else {
			ctx.status(400);
			ctx.result("Something went wrong with your submission. Please try again.");
		}
		
	}
	
	/**
	 * Sends an HTTP response containing the reimbursement requests
	 * associated with a particular employee who submitted them based
	 * on that employee's ID (which is sent as a path variable).
	 * <p>
	 * If the ID is of the correct format and the employee exists in
	 * the database, the requests are returned with a status code of 200.
	 * <p>
	 * If the ID is of the correct format but the employee does not
	 * exist, a response is sent with a status code of 404 (Not Found)
	 * and a message stating that the user does not exist.
	 * <p>
	 * If the ID is <strong>not</strong> of the correct format, a
	 * response is sent with a status code of 400 (Bad Request) and
	 * a message stating that the ID must be an integer.
	 * 
	 * @param ctx Javalin's Context object representing the HTTP request and response
	 */
	public static void getRequestsByRequestor(Context ctx) {
		String requestorIdStr = ctx.pathParam("id");
		log.info("getting all of the requestor requests: " + requestorIdStr);
		
		
		try {
			int requestorId = Integer.valueOf(requestorIdStr);
			Employee requestor = empServ.getEmployeeById(requestorId);
			//log.info("employee: " + requestor);
			if (requestor != null) {
				//log.info(empServ.getReimbursementRequests(requestor));
				ctx.json(empServ.getReimbursementRequests(requestor));
			} else {
				ctx.status(404);
				ctx.result("The user you specified does not exist.");
			}
		} catch (NumberFormatException e) {
			ctx.status(400);
			ctx.result("Requestor ID must be an integer. Please try again.");
		}
		
	}
}
