package com.revature.app;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.EmployeesController;
import com.revature.controllers.RequestsController;
import com.revature.data.EmployeeDAO;
import com.revature.data.postgres.EmployeePostgres;

public class TRMSApp {

	private static EmployeeDAO empDAO = new EmployeePostgres();
//	private static Employee emp = new Employee();
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start();
		
//		System.out.println(emp);
		
		app.routes(() -> {
			path("/employee", () -> { 
				get(EmployeesController::viewAllEmployees);
				
			path("/id/{empId}", () -> {
					get(EmployeesController::viewEmployeeById);
			
			
			});	
			});
			path("/requests", () -> {
				post(RequestsController::submitReimbursementRequest);
				
			path("/requestor/{id}", () -> {//   /requests/requestor/id
					get(RequestsController::getRequestsByRequestor);
				
			
			});	
			});
		});
	}

	}
