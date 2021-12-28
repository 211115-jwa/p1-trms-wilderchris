package com.revature.app;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.Set;

import com.revature.beans.Employee;
import com.revature.controllers.RequestsController;
import com.revature.data.EmployeeDAO;
import com.revature.data.postgres.EmployeePostgres;
import com.revature.services.EmployeeService;

public class TRMSApp {

	private static EmployeeDAO empDAO = new EmployeePostgres();
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start();
		Set<Employee> emps = empDAO.getAll();
		System.out.println(emps);
		app.routes(() -> {
			path("/requests", () -> {
				post(RequestsController::submitReimbursementRequest);
				path("/requestor/{id}", () -> {
					get(RequestsController::getRequestsByRequestor);
				});
			});
		});
	}

}
