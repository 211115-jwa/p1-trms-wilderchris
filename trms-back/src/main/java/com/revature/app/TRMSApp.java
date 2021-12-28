package com.revature.app;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.util.Set;

import com.revature.beans.Employee;
import com.revature.controllers.RequestsController;
import com.revature.data.EmployeeDAO;
import com.revature.data.postgres.EmployeePostgres;

public class TRMSApp {

	private static EmployeeDAO empDAO = new EmployeePostgres();
	private static Employee emp = new Employee();
	
	public static void main(String[] args) {
		Javalin app = Javalin.create().start();
		
//		System.out.println(emp);
		
		app.routes(() -> {
			path("/requests", () -> {
				post(RequestsController::submitReimbursementRequest);
				path("/requestor/{id}", () -> {
					get(RequestsController::getRequestsByRequestor);
				});
				
				path("/employee", () -> { // get end point for queries and getAll
					get(ctx -> {
						int idSearch = Integer.parseInt(ctx.queryParam("id")); // sets var for model and
						String deptSearch = ctx.queryParam("dept"); // brand if the brand or model key is used
						// as /bikes?model= or /bikes?brand= search by brand if/bikes?brand=
						if (idSearch != 0 ) {//&& !"".equals(idSearch)) {
							emp = empDAO.getById((idSearch));
							if (!"".equals(emp)) // error check for no matches
								ctx.result("No id By That Name!!");
							else
								ctx.json(emp);
//						} else if (deptSearch != null && !"".equals(deptSearch)) {
//							Set<Bike> emps = us.getByModel(deptSearch);
//							if (emps.isEmpty()) // error check for no matches
//								ctx.result("No Departments By That Name!!");
//							else
//								ctx.json(emps);
						} else { // get all  if no params are sent
						Set<Employee> emps = empDAO.getAll();
							ctx.json(emps);
						}
					});
				});
			});
		});
	}

	}
