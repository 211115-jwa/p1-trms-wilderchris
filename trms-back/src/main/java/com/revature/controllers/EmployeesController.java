package com.revature.controllers;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.beans.Employee;
import com.revature.data.EmployeeDAO;
import com.revature.data.postgres.EmployeePostgres;

import io.javalin.http.Context;

public class EmployeesController {
	//private static EmployeeService empServ = new EmployeeServiceImpl();
	private static EmployeeDAO empDAO = new EmployeePostgres();
	private static Employee emp = new Employee();
	
	private static Logger log = LogManager.getLogger(EmployeesController.class);
	
	public static void viewAllEmployees(Context ctx) {
		log.info("getting all of the employees");
		Set<Employee> emps = empDAO.getAll();
		//System.out.println(emps);
		ctx.json(emps);
	}
	public static void viewEmployeeById(Context ctx) {
		log.info("getting employee by id");
		String idString = ctx.pathParam("id");
		try {
			
		emp = empDAO.getById(Integer.valueOf(idString));
		if (idString != null) {
			ctx.json(emp);
		} else {
			ctx.status(404);
			ctx.result("The employee does not exist.");
		}
	} catch (NumberFormatException e) {
		ctx.status(400);
		ctx.result("Employee ID must be an integer. Please try again.");
	}
		
		}
}
