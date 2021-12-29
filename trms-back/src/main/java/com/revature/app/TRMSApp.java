package com.revature.app;

import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

import com.revature.controllers.EmployeesController;
import com.revature.controllers.RequestsController;

public class TRMSApp {

	//private static EmployeeDAO empDAO = new EmployeePostgres();
//	private static Employee emp = new Employee();

	public static void main(String[] args) {
		Javalin app = Javalin.create().start();

		
		
		app.routes(() -> {
													// localhost:8080/pets
			path("/requests", () -> {
				//get(PetsController::getPets);
				post(RequestsController::submitReimbursementRequest);
				
				// localhost:8080/pets/adopt/8
				path("/requestor/{id}", () -> {
					get(RequestsController::getRequestsByRequestor);
					//put(PetsController::adoptPet);
				});
				
				// localhost:8080/pets/8
				path("/{id}", () -> {
					//get(PetsController::getPetById);
				//	put(PetsController::updatePet);
				});
			});
			
			path("/employees", () -> {
				get(EmployeesController::viewAllEmployees);
				//post(UsersController::register); // register
//				path("/auth", () -> {
//					post(UsersController::logIn); // login
				});
				path("/{id}", () -> {// emp get by id
					get(EmployeesController::viewEmployeeById);
					//get(UsersController::getUserById); // get user by id
					//put(UsersController::updateUser); // update user
				//	path("/auth", () -> {
						//get(UsersController::checkLogin); // check login
					//});
				});
			//});
		});
		
		
		
		
		
		
		
//		app.routes(() -> {
//			path("/employee", () -> {
//				get(EmployeesController::viewAllEmployees);
//
//				path("/id/{empId}", () -> {
//					get(EmployeesController::viewEmployeeById);
//
//				});
//			});
//			path("/requests", () -> {
//				post(RequestsController::submitReimbursementRequest);
//
//				path("/requestor/{id}", () -> {// /requests/requestor/id
//					get(RequestsController::getRequestsByRequestor);
//
//				});
//			});
//		});
	}
}
