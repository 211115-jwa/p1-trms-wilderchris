
package com.revature.app;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;
import io.javalin.plugin.json.JsonMapper;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.controllers.EmployeesController;
import com.revature.controllers.RequestsController;
import com.revature.controllers.ReviewsController;
import com.revature.controllers.UsersController;

public class TRMSApp {

/*	
Build a front end to complete the MVP that is provided, allowing users to submit and view requests.
From here, choose at least three features that you want to add to the application based on the
specifications provided. Use BDD with Cucumber to plan the expected behavior of features, 
then implement each feature using Agile methodology. Test each feature that you've added 
with Selenium using your Cucumber feature files.
	
	*/
	//ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
	
	//objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	public static void main(String[] args) {
		Javalin app = Javalin.create(config -> {
			
			config.enableCorsForAllOrigins();
		}).start();

		app.before("/employees/*", ctx -> {
			if (!ctx.method().equals("OPTIONS")) {
				ctx.header("Access-Control-Allow-Headers", "Token");
			    ctx.header("Access-Control-Expose-Headers", "Token");
				
				String token = ctx.header("Token");
				if (token==null) ctx.status(HttpCode.UNAUTHORIZED);
			}
		});
		
		app.routes(() -> {
			path("/pending/{id}", () -> {
				get(RequestsController::getByApprover);

				path("/id/{empId}", () -> {//?
					get(EmployeesController::viewEmployeeById);

				});
			});
			path("/requests", () -> {
				post(RequestsController::submitReimbursementRequest);

				path("/requestor/{id}", () -> {// /requests/requestor/id
					get(RequestsController::getRequestsByRequestor);

				});
			});
		
			path("/users", () -> {
				post(UsersController::register); // register
				path("/auth", () -> {
					post(UsersController::logIn); // login
				});
				path("/{id}", () -> {
					get(UsersController::getUserById); // get user by id
					put(UsersController::updateUser); // update user
					path("/auth", () -> {
						get(UsersController::checkLogin); // check login
					});
				});
			});
		});
	}
	
}

//class JacksonMapper implements JsonMapper {
//	ObjectMapper om = new ObjectMapper();
//	
//	@Override
//    public String toJsonString(Object obj) {
//        try {
//			return om.writeValueAsString(obj);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//        return null;
//    }
//    @Override
//    public <T> T fromJsonString(String json, Class<T> targetClass) {
//        try {
//			return om.readValue(json, targetClass);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//        return null;
//    }
//}
