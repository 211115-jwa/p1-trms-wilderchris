package com.revature.app;

import io.javalin.Javalin;
import io.javalin.plugin.json.JsonMapper;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.EmployeesController;
import com.revature.controllers.RequestsController;

public class TRMSApp {

/*	
Build a front end to complete the MVP that is provided, allowing users to submit and view requests.
From here, choose at least three features that you want to add to the application based on the
specifications provided. Use BDD with Cucumber to plan the expected behavior of features, 
then implement each feature using Agile methodology. Test each feature that you've added 
with Selenium using your Cucumber feature files.
	
	*/
	
	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			config.jsonMapper(new JacksonMapper());
			config.enableCorsForAllOrigins();
		}).start();

		
	app.routes(() -> {
			path("/employees", () -> {
				get(EmployeesController::viewAllEmployees);

				path("/id/{empId}", () -> {
					get(EmployeesController::viewEmployeeById);

				});
			});
			path("/requests", () -> {
				post(RequestsController::submitReimbursementRequest);

				path("/requestor/{id}", () -> {// /requests/requestor/id
					get(RequestsController::getRequestsByRequestor);

				});
			});
		});
	}
	
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
